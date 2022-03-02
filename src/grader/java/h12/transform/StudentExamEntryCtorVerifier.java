package h12.transform;

import h12.StudentExamEntry;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.Printer;
import org.sourcegrade.jagr.api.testing.ClassTransformer;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Enumerates all method invocations in the 3-parameter constructor of {@link StudentExamEntry}.
 */
public class StudentExamEntryCtorVerifier implements ClassTransformer {

    @Override
    public String getName() {
        return "StudentExamEntryCtor-transformer";
    }

    @Override
    public void transform(ClassReader reader, ClassWriter writer) {
        if ("h12/StudentExamEntry".equals(reader.getClassName())) {
            reader.accept(new CV(Opcodes.ASM9, writer), 0);
        } else {
            reader.accept(writer, 0);
        }
    }

    private static class CV extends ClassVisitor {

        public CV(int api, ClassVisitor classVisitor) {
            super(api, classVisitor);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
            if ("<init>".equals(name)
                && "(Ljava/lang/String;Ljava/lang/String;I)V".equals(descriptor)) {
                return new MV(api, super.visitMethod(access, name, descriptor, signature, exceptions));
            }
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }

        private static class MV extends MethodVisitor {

            private final List<String> invokes = new ArrayList<>();

            public MV(int api, MethodVisitor methodVisitor) {
                super(api, methodVisitor);
            }

            @Override
            public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
                invokes.add(String.format("%s %s.%s%s", Printer.OPCODES[opcode], owner, name, descriptor));
                super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
            }

            @Override
            public void visitInsn(int opcode) {
                if (opcode == Opcodes.RETURN) {
                    visitInlineArray();
                }
                super.visitInsn(opcode);
            }

            private void visitInlineArray() {
                visitInt(invokes.size());
                visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/String");
                ListIterator<String> it = invokes.listIterator();
                while (it.hasNext()) {
                    visitArrayElement(it.nextIndex(), it.next());
                }
                super.visitMethodInsn(
                    Opcodes.INVOKESTATIC,
                    "h12/transform/StudentExamEntryMeta",
                    "setCtor3Invokes",
                    "([Ljava/lang/String;)V",
                    false
                );
            }

            private void visitArrayElement(int index, String value) {
                visitInsn(Opcodes.DUP);
                visitInt(index);
                visitLdcInsn(value);
                visitInsn(Opcodes.AASTORE);
            }

            private void visitInt(int value) {
                if (value >= 0 && value <= 5) {
                    visitInsn(Opcodes.ICONST_0 + value);
                } else {
                    visitIntInsn(Opcodes.BIPUSH, value);
                }
            }
        }
    }
}
