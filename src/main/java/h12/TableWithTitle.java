package h12;

public class TableWithTitle {
    private final String title;
    private final StudentExamEntry[] entries;

    public TableWithTitle(String title, StudentExamEntry[] entries) {
        this.title = title;
        this.entries = entries;
    }

    public String getTitle() {
        return title;
    }

    public StudentExamEntry[] getEntries() {
        return entries;
    }
}
