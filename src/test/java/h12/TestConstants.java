package h12;

public final class TestConstants {

    private TestConstants() {
    }

    static final char[] A_Z = new char['z' - 'a' + 1];

    static {
        for (char i = 'a'; i <= 'z'; i++) {
            A_Z[i - 'a'] = i;
        }
    }

    static final String[] VALID_MARKS = {
        "1,0",
        "1,3",
        "1,7",
        "2,0",
        "2,3",
        "2,7",
        "3,0",
        "3,3",
        "3,7",
        "4,0",
        "5,0",
        "n/a",
    };
}
