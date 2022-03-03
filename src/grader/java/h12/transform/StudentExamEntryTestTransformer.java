package h12.transform;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.slf4j.Logger;
import org.sourcegrade.jagr.api.testing.ClassTransformer;
import org.sourcegrade.jagr.launcher.env.Jagr;

import java.util.HashSet;
import java.util.Set;

public class StudentExamEntryTestTransformer implements ClassTransformer {

    @Override
    public String getName() {
        return "StudentEamEntryTestTransformer-transformer";
    }

    @Override
    public void transform(ClassReader reader, ClassWriter writer) {
        if ("h12/StudentExamEntryTest".equals(reader.getClassName())) {
            final TestMarks_Extractor extractor = new TestMarks_Extractor(Opcodes.ASM9);
            reader.accept(extractor, 0);
            reader.accept(new CV(Opcodes.ASM9, writer, extractor.testMarksReplaceInvokeDynamics), 0);
        } else {
            reader.accept(writer, 0);
        }
    }

    private static class TestMarks_Extractor extends ClassVisitor {

        private final Set<Integer> testMarksReplaceInvokeDynamics = new HashSet<>();

        public TestMarks_Extractor(int api) {
            super(api);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
            if ("testMarks".equals(name)) {
                return new MV(api, super.visitMethod(access, name, descriptor, signature, exceptions));
            }
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }

        private class MV extends MethodVisitor {

            int index = -1;
            boolean passedInvokeDynamic;

            public MV(int api, MethodVisitor methodVisitor) {
                super(api, methodVisitor);
            }

            @Override
            public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
                ++index;
                passedInvokeDynamic = true;
                super.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments);
            }

            @Override
            public void visitTypeInsn(int opcode, String type) {
                if (passedInvokeDynamic && opcode == Opcodes.CHECKCAST) {
                    passedInvokeDynamic = false;
                    if ("h12/StudentExamEntry".equals(type)) {
                        testMarksReplaceInvokeDynamics.add(index);
                    }
                }
            }
        }
    }

    private static class CV extends ClassVisitor {

        private final Set<Integer> testMarksReplaceInvokeDynamics;

        public CV(int api, ClassVisitor classVisitor, Set<Integer> testMarksReplaceInvokeDynamics) {
            super(api, classVisitor);
            this.testMarksReplaceInvokeDynamics = testMarksReplaceInvokeDynamics;
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
            MethodVisitor og = super.visitMethod(access, name, descriptor, signature, exceptions);
            if ("testMarks".equals(name)) {
                og = new TestMarksReplaceInvokeDynamics(api, og);
            }
            return new MV(api, og);
        }

        @Override
        public void visitEnd() {
            // creates a synthetic method to be swapped into the INVOKEDYNAMIC targeting
            // a StudentExamEntry local variable in testMarks
            final MethodVisitor mv = super.visitMethod(
                Opcodes.ACC_PRIVATE | Opcodes.ACC_STATIC | Opcodes.ACC_SYNTHETIC,
                "lambda$tutorTestMarks$0",
                "()Lh12/h1_3/SetMark_TutorStudentExamEntry;",
                null,
                new String[]{"java/lang/Throwable"}
            );
            mv.visitCode();
            mv.visitTypeInsn(Opcodes.NEW, "h12/h1_3/SetMark_TutorStudentExamEntry");
            mv.visitInsn(Opcodes.DUP);
            mv.visitLdcInsn("a");
            mv.visitLdcInsn("b");
            mv.visitInsn(Opcodes.ICONST_1);
            mv.visitLdcInsn("n/a");
            mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                "h12/h1_3/SetMark_TutorStudentExamEntry",
                "<init>",
                "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V",
                false
            );
            mv.visitInsn(Opcodes.ARETURN);
            mv.visitMaxs(6, 0);
            mv.visitEnd();
            super.visitEnd();
        }

        private class TestMarksReplaceInvokeDynamics extends MethodVisitor {

            int index;

            public TestMarksReplaceInvokeDynamics(int api, MethodVisitor methodVisitor) {
                super(api, methodVisitor);
            }

            @Override
            public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
                if (opcode == Opcodes.INVOKESPECIAL
                    && "h12/StudentExamEntry".equals(owner)
                    && "<init>".equals(name)) {
                    super.visitMethodInsn(opcode, "h12/h1_3/SetMark_TutorStudentExamEntry", name, descriptor, isInterface);
                } else {
                    super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
                }
            }

            @Override
            public void visitTypeInsn(int opcode, String type) {
                if ((opcode == Opcodes.CHECKCAST || opcode == Opcodes.NEW) && "h12/StudentExamEntry".equals(type)) {
                    super.visitTypeInsn(opcode, "h12/h1_3/SetMark_TutorStudentExamEntry");
                } else {
                    super.visitTypeInsn(opcode, type);
                }
            }

            @Override
            public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
                // if this INVOKEDYNAMIC should be replaced
                if (testMarksReplaceInvokeDynamics.contains(index++)) {
                    super.visitInvokeDynamicInsn(
                        "get",
                        "()Lorg/junit/jupiter/api/function/ThrowingSupplier;",
                        new Handle(
                            Opcodes.H_INVOKESTATIC,
                            "java/lang/invoke/LambdaMetafactory",
                            "metafactory",
                            "("
                                + "Ljava/lang/invoke/MethodHandles$Lookup;"
                                + "Ljava/lang/String;"
                                + "Ljava/lang/invoke/MethodType;"
                                + "Ljava/lang/invoke/MethodType;"
                                + "Ljava/lang/invoke/MethodHandle;"
                                + "Ljava/lang/invoke/MethodType;"
                                + ")Ljava/lang/invoke/CallSite;",
                            false
                        ),
                        Type.getType("()Ljava/lang/Object;"),
                        new Handle(
                            Opcodes.H_INVOKESTATIC,
                            "h12/StudentExamEntryTest",
                            "lambda$tutorTestMarks$0",
                            "()Lh12/h1_3/SetMark_TutorStudentExamEntry;",
                            false
                        ),
                        Type.getType("()Lh12/h1_3/SetMark_TutorStudentExamEntry;")
                    );
                } else {
                    super.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments);
                }
            }
        }

        private static class MV extends MethodVisitor {

            public MV(int api, MethodVisitor methodVisitor) {
                super(api, methodVisitor);
            }

            private boolean checkCastType(String name) {
                return switch (name) {
                    case "java/lang/NullPointerException",
                        "h12/BadStudentMarkException",
                        "h12/BadEnrollmentNumberException",
                        "h12/BadCharException" -> true;
                    default -> false;
                };
            }

            @Override
            public void visitTypeInsn(int opcode, String type) {
                // remove all CHECKCAST insns for certain exception types
                if (opcode != Opcodes.CHECKCAST || !checkCastType(type)) {
                    super.visitTypeInsn(opcode, type);
                }
            }

            @Override
            public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
                if (opcode == Opcodes.INVOKEVIRTUAL
                    && checkCastType(owner)
                    && "getMessage".equals(name)
                    && "()Ljava/lang/String;".equals(descriptor)) {
                    super.visitMethodInsn(opcode, "java/lang/Throwable", name, descriptor, isInterface);
                } else {
                    super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
                }
            }
        }
    }
}
