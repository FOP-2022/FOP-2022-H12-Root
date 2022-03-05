package h12.h2_4;

import h12.FileSystemIOFactory;
import h12.TutorUtils;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.TestCycle;
import org.sourcegrade.jagr.api.testing.extension.TestCycleResolver;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h12")
public class FileSystemIOFactorySignaturesTutorTest {

    @SuppressWarnings("UnstableApiUsage")
    private Class<?> assertClassExists() {
        final @Nullable TestCycle testCycle = TestCycleResolver.getTestCycle();
        if (testCycle == null) {
            return FileSystemIOFactory.class;
        } else {
            return assertDoesNotThrow(() -> testCycle.getClassLoader().loadClass("h12.FileSystemIOFactory"),
                "Class h12.FileSystemIOFactory does not exist");
        }
    }

    @Test
    public void testMethodsExist() {
        Class<?> type = assertClassExists();
        TutorUtils.assertMethod(type, "createReader", BufferedReader.class, String.class);
        TutorUtils.assertMethod(type, "createWriter", BufferedWriter.class, String.class);
        TutorUtils.assertMethod(type, "supportsReader", boolean.class);
        TutorUtils.assertMethod(type, "supportsWriter", boolean.class);
    }

    @Test
    public void testSupportsMethods() {
        Class<?> type = assertClassExists();
        final Object instance = assertDoesNotThrow(() -> type.getConstructor().newInstance(),
            "Could not create instance of " + type.getName() + ". Probably missing public no-args constructor.");
        assertTrue((boolean) assertDoesNotThrow(() -> type.getMethod("supportsReader").invoke(instance)),
            "FileSystemIOFactory#supportsReader() should return true");
        assertTrue((boolean) assertDoesNotThrow(() -> type.getMethod("supportsWriter").invoke(instance)),
            "FileSystemIOFactory#supportsWriter() should return true");
    }
}
