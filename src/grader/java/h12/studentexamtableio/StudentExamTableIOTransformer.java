package h12.studentexamtableio;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.sourcegrade.jagr.api.testing.ClassTransformer;

public class StudentExamTableIOTransformer implements ClassTransformer {
    @Override
    public String getName() {
        return "StudentExamTableIO";
    }

    @Override
    public void transform(final ClassReader reader, final ClassWriter writer) {
        reader.accept(new SwitcherooCV(Opcodes.ASM9, writer), 0);
    }

    private static class SwitcherooCV extends ClassVisitor {

        public SwitcherooCV(final int api, final ClassVisitor classVisitor) {
            super(api, classVisitor);
        }

        @Override
        public MethodVisitor visitMethod(final int access, final String name, final String descriptor,
                                         final String signature, final String[] exceptions) {
            return new SwitcherooMV(api, super.visitMethod(access, name, descriptor, signature, exceptions));
        }

        private static class SwitcherooMV extends MethodVisitor {

            public SwitcherooMV(final int api, final MethodVisitor methodVisitor) {
                super(api, methodVisitor);
            }

            @Override
            public void visitMethodInsn(final int opcode, final String owner, final String name,
                                        final String descriptor, final boolean isInterface) {
                if (opcode == Opcodes.INVOKESTATIC
                    && "h12/StudentExamTableIO".equals(owner)
                    && (matchesWriteTable(name, descriptor)
                    || matchesWriteEntry(name, descriptor)
                    || matchesReadTable(name, descriptor)
                    || matchesReadEntry(name, descriptor))) {
                    super.visitMethodInsn(opcode, "h12/studentexamtableio/TutorStudentExamTableIO", name,
                        descriptor, isInterface);
                } else {
                    super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
                }
            }

            private static boolean matchesWriteTable(String name, String descriptor) {
                return "writeStudentExamTable".equals(name)
                    && ("(Ljava/io/Writer;[Lh12/StudentExamEntry;Ljava/lang/String;)V".equals(descriptor)
                    || "(Ljava/io/Writer;[Lh12/StudentExamEntry;)V".equals(descriptor));
            }

            private static boolean matchesWriteEntry(String name, String descriptor) {
                return "writeStudentExamEntry".equals(name)
                    && "(Ljava/io/Writer;Lh12/StudentExamEntry;)V".equals(descriptor);
            }

            private static boolean matchesReadTable(String name, String descriptor) {
                return "readStudentExamTable".equals(name)
                    && "(Ljava/io/BufferedReader;)Lh12/TableWithTitle;".equals(descriptor);
            }

            private static boolean matchesReadEntry(String name, String descriptor) {
                return "readStudentExamEntry".equals(name)
                    && "(Ljava/lang/String;)Lh12/StudentExamEntry;".equals(descriptor);
            }
        }
    }
}
