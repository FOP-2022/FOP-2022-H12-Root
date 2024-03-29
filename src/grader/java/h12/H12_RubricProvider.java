package h12;

import h12.h1_1.BadCharExceptionTutorTest;
import h12.h1_1.BadEnrollmentNumberExceptionTutorTest;
import h12.h1_1.BadStudentMarkExceptionTutorTest;
import h12.h1_1.ExceptionTest;
import h12.h1_1.StudentExamEntryCtorVerifier;
import h12.h1_1.StudentExamEntryGeneralTutorTest;
import h12.h1_1.StudentExamEntrySignaturesTutorTest;
import h12.h1_2.StudentExamEntryEqualsTutorTest;
import h12.h1_3.StudentExamEntryTestFunctionTutorTest;
import h12.h1_3.StudentExamEntryTestSignaturesTutorTest;
import h12.h1_3.StudentExamEntryTestTransformer;
import h12.h2_1.StudentExamTableIOWriteEntryTutorTest;
import h12.h2_1.StudentExamTableIOWriteTable2TutorTest;
import h12.h2_1.StudentExamTableIOWriteTable3TutorTest;
import h12.h2_1.TableWithTitleGeneralTutorTest;
import h12.h2_1.TableWithTitleSignaturesTutorTest;
import h12.h2_2and3.TableGeneratorEntriesTutorTest;
import h12.h2_2and3.TableGeneratorSignaturesTutorTest;
import h12.h2_2and3.TableGeneratorTableTutorTest;
import h12.h2_4.FileSystemIOFactoryGeneralTutorTest;
import h12.h2_4.FileSystemIOFactorySignaturesTutorTest;
import h12.h2_5.TestWriteStudentExamTableTutorTest;
import h12.h3_1.StudentExamTableIOReadEntryTutorTest;
import h12.h3_1.StudentExamTableIOReadTableTutorTest;
import h12.h3_2.TestReadStudentExamTableTutorTest;
import h12.h3_3.TestReadStudentExamEntryTutorTest;
import h12.h4_1.TestWriteStudentExamTableComplexTutorTest;
import h12.h4_2.TestWriteAndReadStudentExamTableTutorTest;
import h12.io.FileSystemIOFactoryTransformer;
import h12.studentexamtableio.StudentExamTableIOSignaturesTutorTest;
import h12.studentexamtableio.StudentExamTableIOTransformer;
import h12.tablegenerator.TableGeneratorTransformer;
import h12.tableiotest.StudentExamTableIOTestSignaturesTutorTest;
import h12.tableiotest.StudentExamTableIOTestTransformer;
import h12.transform.AccessTransformer;
import h12.transform.TutorAssertions;
import h12.transform.TutorAssumptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
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
        .shortDescription("Attribute und methoden existieren")
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
        .shortDescription("Methoden existieren und testTestConstructorsWork korrekt")
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
        .shortDescription("testMarks korrekt")
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

    public static final Criterion H1 = Criterion.builder()
        .shortDescription("H1")
        .addChildCriteria(H1_1, H1_2, H1_3)
        .build();

    public static final Criterion H2_1_TableWithTitle_Signatures = Criterion.builder()
        .shortDescription("TableWithTitle attribute, konstruktor und methoden existieren")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                TableWithTitleSignaturesTutorTest.class.getMethod("testAttributesExist")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                TableWithTitleSignaturesTutorTest.class.getMethod("testConstructorExists")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                TableWithTitleSignaturesTutorTest.class.getMethod("testGettersExist")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_1_TableWithTitle_Correct = Criterion.builder()
        .shortDescription("TableWithTitle vollständig korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                TableWithTitleSignaturesTutorTest.class.getMethod("testAttributesExist")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                TableWithTitleSignaturesTutorTest.class.getMethod("testConstructorExists")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                TableWithTitleSignaturesTutorTest.class.getMethod("testGettersExist")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                TableWithTitleGeneralTutorTest.class.getMethod("testFieldsAndGetters")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static Criterion H2_1_Entry_Signature = Criterion.builder()
        .shortDescription("writeStudentExamEntry existiert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamTableIOSignaturesTutorTest.class.getMethod("testWriteStudentExamEntryExists")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static Criterion H2_1_Entry_Correct = Criterion.builder()
        .shortDescription("writeStudentExamEntry vollständig korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamTableIOWriteEntryTutorTest.class.getMethod("testWriteStudentExamEntry")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static Criterion H2_1_Table_Signatures = Criterion.builder()
        .shortDescription("writeStudentExamTable existiert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamTableIOSignaturesTutorTest.class.getMethod("testWriteStudentExamTableExists")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static Criterion H2_1_Table_Basic = Criterion.builder()
        .shortDescription("writeStudentExamTable funktioniert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamTableIOSignaturesTutorTest.class.getMethod("testWriteStudentExamTableExists")))
            .requirePass(
                JUnitTestRef.or(
                    JUnitTestRef.ofMethod(() ->
                        StudentExamTableIOWriteTable2TutorTest.class.getMethod("testWriteStudentExamTable2")),
                    JUnitTestRef.ofMethod(() ->
                        StudentExamTableIOWriteTable3TutorTest.class.getMethod("testWriteStudentExamTable3"))
                )
            )
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static Criterion H2_1_Table_Complex = Criterion.builder()
        .shortDescription("writeStudentExamTable vollständig korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamTableIOSignaturesTutorTest.class.getMethod("testWriteStudentExamTableExists")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamTableIOWriteTable2TutorTest.class.getMethod("testWriteStudentExamTable2")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamTableIOWriteTable3TutorTest.class.getMethod("testWriteStudentExamTable3")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_1 = Criterion.builder()
        .shortDescription("H2.1 StudentExamTableIO")
        .addChildCriteria(new Criterion[]{
            H2_1_TableWithTitle_Signatures,
            H2_1_TableWithTitle_Correct,
            H2_1_Entry_Signature,
            H2_1_Entry_Correct,
            H2_1_Table_Signatures,
            H2_1_Table_Basic,
            H2_1_Table_Complex,
        }).build();

    public static final Criterion H2_2_TableGenerator_Signatures = Criterion.builder()
        .shortDescription("TableGenerator#createEntries(int, long) existiert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                TableGeneratorSignaturesTutorTest.class.getMethod("testCreateEntriesExists")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_2_TableGenerator_Entries_Basic = Criterion.builder()
        .shortDescription("TableGenerator#createEntries(int, long) level 1")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                TableGeneratorEntriesTutorTest.class.getMethod("testCreateEntriesBasic")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_2_TableGenerator_Entries_Mid = Criterion.builder()
        .shortDescription("TableGenerator#createEntries(int, long) level 2")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                TableGeneratorEntriesTutorTest.class.getMethod("testCreateEntriesMid")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_2_TableGenerator_Entries_Complex = Criterion.builder()
        .shortDescription("TableGenerator#createEntries(int, long) level 3")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                TableGeneratorEntriesTutorTest.class.getMethod("testCreateEntriesComplex")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_2 = Criterion.builder()
        .shortDescription("H2.2 TableGenerator createEntries")
        .addChildCriteria(new Criterion[]{
            H2_2_TableGenerator_Signatures,
            H2_2_TableGenerator_Entries_Basic,
            H2_2_TableGenerator_Entries_Mid,
            H2_2_TableGenerator_Entries_Complex,
        }).build();

    public static final Criterion H2_3_Signatures = Criterion.builder()
        .shortDescription("TableGenerator#createTable(int, long) existiert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                TableGeneratorSignaturesTutorTest.class.getMethod("testCreateTableExists")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_3_Function = Criterion.builder()
        .shortDescription("TableGenerator#createTable(int, long) vollständig korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                TableGeneratorTableTutorTest.class.getMethod("testCreateTableBasic")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                TableGeneratorTableTutorTest.class.getMethod("testCreateTableMid")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                TableGeneratorTableTutorTest.class.getMethod("testCreateTableComplex")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_3 = Criterion.builder()
        .shortDescription("H2.3 TableGenerator createTable")
        .addChildCriteria(H2_3_Signatures, H2_3_Function)
        .build();

    public static final Criterion H2_4_Basic = Criterion.builder()
        .shortDescription("FileSystemIOFactory signaturen und supports Methoden korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                FileSystemIOFactorySignaturesTutorTest.class.getMethod("testMethodsExist")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                FileSystemIOFactorySignaturesTutorTest.class.getMethod("testSupportsMethods")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_4_Reader_Writer = Criterion.builder()
        .shortDescription("FileSystemIOFactory#createReader(String) und FileSystemIOFactory#createWriter(String) korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                FileSystemIOFactoryGeneralTutorTest.class.getMethod("testFileSystemIOFactoryReader")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                FileSystemIOFactoryGeneralTutorTest.class.getMethod("testFileSystemIOFactoryWriter")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_4 = Criterion.builder()
        .shortDescription("H2.4")
        .addChildCriteria(H2_4_Basic, H2_4_Reader_Writer)
        .build();

    public static final Criterion H2_5_Signatures = Criterion.builder()
        .shortDescription("StudentExamTableIOTest und testWriteStudentExamTableExists existieren")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamTableIOTestSignaturesTutorTest.class.getMethod("testTestWriteStudentExamTableExists")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_5_Complete = Criterion.builder()
        .shortDescription("StudentExamTableIOTest#testWriteStudentExamTable")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                TestWriteStudentExamTableTutorTest.class.getMethod("testAssumption")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                TestWriteStudentExamTableTutorTest.class.getMethod("testFunction")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_5 = Criterion.builder()
        .shortDescription("H2.5 StudentExamTableIOTest ioFactory und testWriteStudentExamTable")
        .addChildCriteria(H2_5_Signatures, H2_5_Complete)
        .build();

    public static final Criterion H2 = Criterion.builder()
        .shortDescription("H2")
        .addChildCriteria(new Criterion[]{
            H2_1,
            H2_2,
            H2_3,
            H2_4,
            H2_5,
        }).build();

    public static final Criterion H3_1_Entry_Signature = Criterion.builder()
        .shortDescription("readStudentExamEntry existiert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamTableIOSignaturesTutorTest.class.getMethod("testReadStudentExamEntryExists")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H3_1_Entry_Works = Criterion.builder()
        .shortDescription("readStudentExamEntry funktioniert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamTableIOReadEntryTutorTest.class.getMethod("testReadStudentExamEntry")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H3_1_Table_Signature = Criterion.builder()
        .shortDescription("readStudentExamTable existiert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamTableIOSignaturesTutorTest.class.getMethod("testReadStudentExamTableExists")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H3_1_Table_Works = Criterion.builder()
        .shortDescription("readStudentExamTable funktioniert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamTableIOReadTableTutorTest.class.getMethod("testReadStudentExamTableNoTitle")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamTableIOReadTableTutorTest.class.getMethod("testReadStudentExamTableWithTitle")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H3_1 = Criterion.builder()
        .shortDescription("H3.1 StudentExamTableIO readStudentExamEntry & readStudentExamTable")
        .addChildCriteria(new Criterion[]{
            H3_1_Entry_Signature,
            H3_1_Entry_Works,
            H3_1_Table_Signature,
            H3_1_Table_Works,
        }).build();

    public static final Criterion H3_2_Works = Criterion.builder()
        .shortDescription("testReadStudentExamTable funktioniert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamTableIOTestSignaturesTutorTest.class.getMethod("testTestReadStudentExamTableExists")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                TestReadStudentExamTableTutorTest.class.getMethod("testAssumption")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                TestReadStudentExamTableTutorTest.class.getMethod("testFunction")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H3_2 = Criterion.builder()
        .shortDescription("H3.2 StudentExamTableIOTest testReadStudentExamTable")
        .addChildCriteria(H3_2_Works)
        .build();

    public static final Criterion H3_3_Signatures = Criterion.builder()
        .shortDescription("testReadStudentExamEntry existiert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamTableIOTestSignaturesTutorTest.class.getMethod("testTestReadStudentExamEntryExists")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H3_3_Works = Criterion.builder()
        .shortDescription("testReadStudentExamEntry funktioniert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                TestReadStudentExamEntryTutorTest.class.getMethod("testFunction")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H3_3 = Criterion.builder()
        .shortDescription("H3.3 StudentExamTableIOTest testReadStudentExamEntry")
        .addChildCriteria(H3_3_Signatures, H3_3_Works)
        .build();

    public static final Criterion H3 = Criterion.builder()
        .shortDescription("H3")
        .addChildCriteria(H3_1, H3_2, H3_3)
        .build();

    public static final Criterion H4_1_Signature = Criterion.builder()
        .shortDescription("testWriteStudentExamTableComplex existiert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamTableIOTestSignaturesTutorTest.class.getMethod("testTestWriteStudentExamTableComplexExists")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H4_1_Works = Criterion.builder()
        .shortDescription("testWriteStudentExamTableComplex funktioniert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                TestWriteStudentExamTableComplexTutorTest.class.getMethod("testAssumption")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                TestWriteStudentExamTableComplexTutorTest.class.getMethod("testFunction")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H4_1 = Criterion.builder()
        .shortDescription("H4.1 StudentExamTableIOTest testWriteStudentExamTableComplex")
        .addChildCriteria(H4_1_Signature, H4_1_Works)
        .build();

    public static final Criterion H4_2_Signature = Criterion.builder()
        .shortDescription("testWriteAndReadStudentExamTable existiert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                StudentExamTableIOTestSignaturesTutorTest.class.getMethod("testTestWriteAndReadStudentExamTableExists")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H4_2_Basic = Criterion.builder()
        .shortDescription("testWriteAndReadStudentExamTable funktioniert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                TestWriteAndReadStudentExamTableTutorTest.class.getMethod("testAssumption")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                TestWriteAndReadStudentExamTableTutorTest.class.getMethod("testBasic")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H4_2_Complex = Criterion.builder()
        .shortDescription("testWriteAndReadStudentExamTable vollständig korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                TestWriteAndReadStudentExamTableTutorTest.class.getMethod("testAssumption")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                TestWriteAndReadStudentExamTableTutorTest.class.getMethod("testBasic")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                TestWriteAndReadStudentExamTableTutorTest.class.getMethod("testComplex")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H4_2 = Criterion.builder()
        .shortDescription("H4.2")
        .addChildCriteria(H4_2_Signature, H4_2_Basic, H4_2_Complex)
        .build();

    public static final Criterion H4 = Criterion.builder()
        .shortDescription("H4")
        .addChildCriteria(H4_1, H4_2)
        .build();

    @Override
    public Rubric getRubric() {
        return Rubric.builder()
            .title("h12")
            .addChildCriteria(H1, H2, H3, H4)
            .build();
    }

    @Override
    public void configure(RubricConfiguration configuration) {
        configuration.addTransformer(new StudentExamEntryCtorVerifier(), ClassTransformerOrder.PRE);
        configuration.addTransformer(new AccessTransformer());
        configuration.addTransformer(ClassTransformer.replacement(TutorAssertions.class, Assertions.class));
        configuration.addTransformer(ClassTransformer.replacement(TutorAssumptions.class, Assumptions.class));
        configuration.addTransformer(new StudentExamTableIOTransformer());
        configuration.addTransformer(new StudentExamTableIOTestTransformer());
        configuration.addTransformer(new StudentExamEntryTestTransformer());
        configuration.addTransformer(new TableGeneratorTransformer());
        configuration.addTransformer(new FileSystemIOFactoryTransformer());
    }
}
