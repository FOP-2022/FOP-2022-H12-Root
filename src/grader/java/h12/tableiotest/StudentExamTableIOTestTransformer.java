package h12.tableiotest;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.sourcegrade.jagr.api.testing.ClassTransformer;

/**
 * Replaces instance saved in StudentExamTableIOTest#ioFactory with {@link TutorIOFactory}.
 */
public class StudentExamTableIOTestTransformer implements ClassTransformer {

    @Override
    public String getName() {
        return "StudentExamTableIOTest-transformer";
    }

    @Override
    public void transform(ClassReader reader, ClassWriter writer) {
        if ("h12/StudentExamTableIOTest".equals(reader.getClassName())) {
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
            if ("<init>".equals(name)) {
                return new MV(api, super.visitMethod(access, name, descriptor, signature, exceptions));
            }
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }

        private static class MV extends MethodVisitor {

            public MV(int api, MethodVisitor methodVisitor) {
                super(api, methodVisitor);
            }

            @Override
            public void visitTypeInsn(int opcode, String type) {
                if (opcode == Opcodes.NEW
                    && ("h12/FileSystemIOFactory".equals(type) || "h12/ResourceIOFactory".equals(type))) {
                    super.visitTypeInsn(opcode, "h12/tableiotest/TutorIOFactory");
                } else {
                    super.visitTypeInsn(opcode, type);
                }
            }

            @Override
            public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
                if (opcode == Opcodes.INVOKESPECIAL
                    && ("h12/FileSystemIOFactory".equals(owner) || "h12/ResourceIOFactory".equals(owner))
                    && "<init>".equals(name)) {
                    if (!descriptor.equals("()V")) {
                        throw new IllegalStateException("Used incorrect constructor for IOFactory implementation: " + descriptor);
                    }
                    super.visitMethodInsn(
                        opcode,
                        "h12/tableiotest/TutorIOFactory",
                        name,
                        "()V",
                        false
                    );
                } else {
                    super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
                }
            }
        }
    }
}
