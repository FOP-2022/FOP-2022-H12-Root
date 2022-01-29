package h12;

import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;

public class TableWithTitle {
    private final @Nullable String title;
    private final StudentExamEntry[] entries;

    public TableWithTitle(@Nullable String title, StudentExamEntry[] entries) {
        this.title = title;
        this.entries = entries;
    }

    public @Nullable String getTitle() {
        return title;
    }

    public StudentExamEntry[] getEntries() {
        return entries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableWithTitle that = (TableWithTitle) o;
        return Objects.equals(title, that.title) && Arrays.equals(entries, that.entries);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(title);
        result = 31 * result + Arrays.hashCode(entries);
        return result;
    }
}
