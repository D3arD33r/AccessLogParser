import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LogEntryParseTest {

    private static final String VALID_LINE = "127.0.0.1 - - [10/Oct/2000:13:55:36 -0700] \"GET /apache_pb.gif HTTP/1.0\" 200 2326 \"-\" \"Mozilla/4.08 [en] (Win98; I ;Nav)\"";

    @Test
    void parseValidLine() {
        LogEntry entry = LogEntry.parse(VALID_LINE);
        assertEquals("127.0.0.1", entry.getIpAddress());
        assertEquals("GET /apache_pb.gif HTTP/1.0", entry.getRequest());
        assertEquals(200, entry.getStatus());
        assertEquals(2326, entry.getBytes());
        assertEquals("Mozilla/4.08 [en] (Win98; I ;Nav)", entry.getUserAgent());
    }

    @Test
    void parseMalformedLine() {
        String malformed = "bad line";
        assertThrows(IllegalArgumentException.class, () -> LogEntry.parse(malformed));
    }

    @Test
    void parseTooLongLine() {
        String longLine = VALID_LINE + "a".repeat(1024 - VALID_LINE.length() + 1);
        assertThrows(IllegalArgumentException.class, () -> LogEntry.parse(longLine));
    }
}
