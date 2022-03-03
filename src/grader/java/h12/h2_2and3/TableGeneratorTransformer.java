package h12.h2_2and3;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.sourcegrade.jagr.api.testing.ClassTransformer;

public class TableGeneratorTransformer implements ClassTransformer {
    @Override
    public String getName() {
        return "TableGenerator";
    }

    @Override
    public void transform(final ClassReader reader, final ClassWriter writer) {
        if ("h12/TableGenerator".equals(reader.getClassName())) {
            reader.accept(new CV(Opcodes.ASM9, writer), 0);
        } else {
            reader.accept(writer, 0);
        }
    }

    private static class CV extends ClassVisitor {

        public CV(final int api, final ClassVisitor classVisitor) {
            super(api, classVisitor);
        }

        @Override
        public MethodVisitor visitMethod(final int access, final String name, final String descriptor, final String signature, final String[] exceptions) {
            MethodVisitor mv = new MV(api, super.visitMethod(access, name, descriptor, signature, exceptions));
            if ("createTable".equals(name)
                && "(IJ)Lh12/TableWithTitle;".equals(descriptor)) {
                mv = new SwitcherooMV(api, mv);
            }
            return mv;
        }

        private static class MV extends MethodVisitor {

            public MV(final int api, final MethodVisitor methodVisitor) {
                super(api, methodVisitor);
            }

            @Override
            public void visitTypeInsn(final int opcode, final String type) {
                if (opcode == Opcodes.NEW && "h12/StudentExamEntry".equals(type)) {
                    super.visitTypeInsn(opcode, "h12/h2_2and3/H2_2_TutorStudentExamEntry");
                } else {
                    super.visitTypeInsn(opcode, type);
                }
            }

            @Override
            public void visitMethodInsn(final int opcode, final String owner, final String name,
                                        final String descriptor, final boolean isInterface) {
                if (opcode == Opcodes.INVOKESPECIAL
                    && "h12/StudentExamEntry".equals(owner)
                    && "<init>".equals(name)) {
                    super.visitMethodInsn(opcode, "h12/h2_2and3/H2_2_TutorStudentExamEntry", name, descriptor, isInterface);
                } else {
                    super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
                }
            }
        }

        private static class SwitcherooMV extends MethodVisitor {

            public SwitcherooMV(final int api, final MethodVisitor methodVisitor) {
                super(api, methodVisitor);
            }

            @Override
            public void visitMethodInsn(final int opcode, final String owner, final String name,
                                        final String descriptor, final boolean isInterface) {
                if (opcode == Opcodes.INVOKESTATIC
                    && "h12/TableGenerator".equals(owner)
                    && "createEntries".equals(name)
                    && ("(IJ)[Lh12/StudentExamEntry;".equals(descriptor)
                    || "(ILjava/util/Random;)[Lh12/StudentExamEntry;".equals(descriptor))) {
                    super.visitMethodInsn(opcode, "h12/h2_2and3/TableGeneratorSwitcheroo", name, descriptor, isInterface);
                } else {
                    super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
                }
            }
        }
    }
}
