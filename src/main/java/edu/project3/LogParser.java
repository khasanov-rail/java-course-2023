package edu.project3;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("checkstyle:RegexpSinglelineJava")
public class LogParser {
    private static final Pattern LOG_PATTERN = Pattern.compile(
        "^(\\S+) - (\\S+) \\[(.+)\\] \"(.*?)\" (\\d{3}) (\\d+|-)"
    );

    private static final int REMOTE_ADDR_GROUP = 1;
    private static final int REMOTE_USER_GROUP = 2;
    private static final int TIME_LOCAL_GROUP = 3;
    private static final int REQUEST_GROUP = 4;
    private static final int STATUS_GROUP = 5;
    private static final int BODY_BYTES_SENT_GROUP = 6;

    public List<LogRecord> parse(List<String> logLines) {
        List<LogRecord> records = new ArrayList<>();
        for (String line : logLines) {
            Matcher matcher = LOG_PATTERN.matcher(line);
            if (matcher.find()) {
                String request = matcher.group(REQUEST_GROUP);
                int status = Integer.parseInt(matcher.group(STATUS_GROUP));
                int bodyBytesSent = parseBodyBytesSent(matcher.group(BODY_BYTES_SENT_GROUP));
                OffsetDateTime timeLocal;

                try {
                    timeLocal = parseTimeLocal(matcher.group(TIME_LOCAL_GROUP));
                } catch (DateTimeParseException e) {
                    throw e;
                }

                LogRecord logRecord = new LogRecord.Builder()
                    .remoteAddr(matcher.group(REMOTE_ADDR_GROUP))
                    .remoteUser(matcher.group(REMOTE_USER_GROUP))
                    .timeLocal(timeLocal)
                    .request(request)
                    .status(status)
                    .bodyBytesSent(bodyBytesSent)
                    .build();

                records.add(logRecord);
            }
        }
        return records;
    }

    private OffsetDateTime parseTimeLocal(String timeLocalStr) throws DateTimeParseException {
        return OffsetDateTime.parse(
            timeLocalStr,
            DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH)
        );
    }

    private int parseBodyBytesSent(String bodyBytesSentStr) {
        return bodyBytesSentStr.equals("-") ? 0 : Integer.parseInt(bodyBytesSentStr);
    }
}
