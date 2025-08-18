import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a parsed line from an access log.
 */
public class LogEntry {
    private final String ipAddress;
    private final String request;
    private final int status;
    private final long bytes;
    private final String userAgent;

    private LogEntry(String ipAddress, String request, int status, long bytes, String userAgent) {
        this.ipAddress = ipAddress;
        this.request = request;
        this.status = status;
        this.bytes = bytes;
        this.userAgent = userAgent;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getRequest() {
        return request;
    }

    public int getStatus() {
        return status;
    }

    public long getBytes() {
        return bytes;
    }

    public String getUserAgent() {
        return userAgent;
    }

    private static final Pattern LOG_PATTERN = Pattern.compile(
            "^(\\S+) \\S+ \\S+ \\[(.+?)\\] \"(.+?)\" (\\d{3}) (\\d+|-) \"(.*?)\" \"(.*?)\"$");

    /**
     * Parses a single line of a web server access log in the combined format.
     *
     * @param line log line
     * @return parsed {@link LogEntry}
     * @throws IllegalArgumentException if the line is malformed or exceeds 1024 characters
     */
    public static LogEntry parse(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Line is null");
        }
        if (line.length() > 1024) {
            throw new IllegalArgumentException("Line is longer than 1024 characters");
        }
        Matcher matcher = LOG_PATTERN.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Malformed log line");
        }
        String ip = matcher.group(1);
        String request = matcher.group(3);
        int status = Integer.parseInt(matcher.group(4));
        String bytesStr = matcher.group(5);
        long bytes = "-".equals(bytesStr) ? 0 : Long.parseLong(bytesStr);
        String userAgent = matcher.group(7);
        return new LogEntry(ip, request, status, bytes, userAgent);
    }
}
