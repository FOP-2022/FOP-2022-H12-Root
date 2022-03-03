package h12;

import h12.h1_1.BadCharExceptionTutorTest;
import h12.h1_1.BadEnrollmentNumberExceptionTutorTest;
import h12.h1_1.BadStudentMarkExceptionTutorTest;
import h12.h1_1.ExceptionTest;
import h12.h1_1.StudentExamEntryGeneralTutorTest;
import h12.h1_1.StudentExamEntrySignaturesTutorTest;
import h12.h1_2.StudentExamEntryEqualsTutorTest;
import h12.h1_3.StudentExamEntryTestFunctionTutorTest;
import h12.h1_3.StudentExamEntryTestSignaturesTutorTest;
import h12.transform.AccessTransformer;
import h12.transform.StudentExamEntryTestTransformer;
import h12.transform.StudentExamEntryCtorVerifier;
import h12.transform.TutorAssertions;
import org.junit.jupiter.api.Assertions;
import org.sourcegrade.jagr.api.rubric.Criterion;
import org.sourcegrade.jagr.api.rubric.Grader;
import org.sourcegrade.jagr.api.rubric.JUnitTestRef;
import org.sourcegrade.jagr.api.rubric.Rubric;
import org.sourcegrade.jagr.api.rubric.RubricForSubmission;
import org.sourcegrade.jagr.api.rubric.RubricProvider;
import org.sourcegrade.jagr.api.testing.ClassTransformer;
import org.sourcegrade.jagr.api.testing.ClassTransformerOrder;
import org.sourcegrade.jagr.api.testing.RubricConfiguration;

@RubricForSubmission("h12")
public class H12_RubricProvider implements RubricProvider {

    /* ========================================== *
     * =================== H1 =================== *
     * ========================================== */

    private static Grader createExceptionGrader(Class<? extends ExceptionTest> exceptionTestType) {
        return Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> exceptionTestType.getMethod("testMessageCorrect")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build();
    }

    public static final Criterion H1_1_BadStudentMarkException = Criterion.builder()
        .shortDescription("BadStudentMarkException richtig")
        .grader(createExceptionGrader(BadStudentMarkExceptionTutorTest.class))
        .build();

    public static final Criterion H1_1_BadEnrollmentNumberExceptionTutorTest = Criterion.builder()
        .shortDescription("BadEnrollmentNumberException richtig")
        .grader(createExceptionGrader(BadEnrollmentNumberExceptionTutorTest.class))
        .build();

    public static final Criterion H1_1_BadCharExceptionTutorTest = Criterion.builder()
        .shortDescription("BadCharException richtig")
        .grader(createExceptionGrader(BadCharExceptionTutorTest.class))
        .build();

    public static final Criterion H1_1_Fields_And_Signatures = Criterion.builder()
        .shortDescription("Attribute und methoden signaturen richtig")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamEntrySignaturesTutorTest.class.getMethod("testAttributesExist")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamEntrySignaturesTutorTest.class.getMethod("testConstructorsExist")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamEntrySignaturesTutorTest.class.getMethod("testGettersExist")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_1_Constructor_Basic_Correct = Criterion.builder()
        .shortDescription("Konstruktoren, getters und setMark funktionieren mit richtige Werte")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamEntryGeneralTutorTest.class.getMethod("testConstructor3Correct")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamEntryGeneralTutorTest.class.getMethod("testFieldsAndGetters")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamEntryGeneralTutorTest.class.getMethod("testSetMark")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_1_Constructor_Exceptions = Criterion.builder()
        .shortDescription("Konstruktoren, getters und setMark sind vollständig korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamEntryGeneralTutorTest.class.getMethod("testConstructor3Correct")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamEntryGeneralTutorTest.class.getMethod("testFieldsAndGetters")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamEntryGeneralTutorTest.class.getMethod("testSetMark")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamEntryGeneralTutorTest.class.getMethod("testConstructorThrows")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamEntryGeneralTutorTest.class.getMethod("testSetMarkThrows")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_1 = Criterion.builder()
        .shortDescription("H1.1 StudentExamEntry & Exceptions")
        .addChildCriteria(new Criterion[]{
            H1_1_BadStudentMarkException,
            H1_1_BadEnrollmentNumberExceptionTutorTest,
            H1_1_BadCharExceptionTutorTest,
            H1_1_Fields_And_Signatures,
            H1_1_Constructor_Basic_Correct,
            H1_1_Constructor_Exceptions,
        }).build();

    public static final Criterion H1_2_StudentExamEntry_Equals_Basic = Criterion.builder()
        .shortDescription("StudentExamEntry.equals funktioniert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamEntryEqualsTutorTest.class.getMethod("testBasic")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_2_StudentExamEntry_Equals_Complex = Criterion.builder()
        .shortDescription("StudentExamEntry.equals funktioniert vollständig korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamEntryEqualsTutorTest.class.getMethod("testBasic")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamEntryEqualsTutorTest.class.getMethod("testComplex")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_2 = Criterion.builder()
        .shortDescription("H1.2 StudentExamEntry.equals")
        .addChildCriteria(H1_2_StudentExamEntry_Equals_Basic, H1_2_StudentExamEntry_Equals_Complex)
        .build();

    public static final Criterion H1_3_Signatures = Criterion.builder()
        .shortDescription("Methoden-Signaturen und testTestConstructorsWork korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamEntryTestSignaturesTutorTest.class.getMethod("testSignaturesCorrect")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamEntryTestFunctionTutorTest.class.getMethod("testTestConstructorsWork")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_3_TestConstructorsThrow = Criterion.builder()
        .shortDescription("testConstructorsThrow korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamEntryTestFunctionTutorTest.class.getMethod("testTestConstructorsThrowSimple")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamEntryTestFunctionTutorTest.class.getMethod("testTestConstructorsThrowComplex")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_3_TestMarks = Criterion.builder()
        .shortDescription("testMarks funktioniert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamEntryTestFunctionTutorTest.class.getMethod("testTestMarks")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_3 = Criterion.builder()
        .shortDescription("H1.3 StudentExamEntryTest")
        .addChildCriteria(H1_3_Signatures, H1_3_TestConstructorsThrow, H1_3_TestMarks)
        .build();

    @Override
    public Rubric getRubric() {
        return Rubric.builder()
            .title("h12")
            .addChildCriteria(H1_1, H1_2, H1_3)
            .build();
    }

    @Override
    public void configure(RubricConfiguration configuration) {
//        configuration.addTransformer(new StudentExamTableIOTestTransformer());
        configuration.addTransformer(new StudentExamEntryCtorVerifier(), ClassTransformerOrder.PRE);
        configuration.addTransformer(new AccessTransformer());
        configuration.addTransformer(ClassTransformer.replacement(TutorAssertions.class, Assertions.class));
        configuration.addTransformer(new StudentExamEntryTestTransformer());
    }
}
