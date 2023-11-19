package edu.project3;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

@SuppressWarnings("checkstyle:RegexpSinglelineJava")
public class Main {
    private static final String OPTION_PATH = "path";
    private static final String OPTION_FORMAT = "format";
    private static final String HELP_MESSAGE = "java -jar nginx-log-stats.jar";
    private static final String FORMAT_MARKDOWN = "markdown";

    private Main() {
    }

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("p", OPTION_PATH, true, "Путь к файлу лога");
        options.addOption("f", OPTION_FORMAT, true, "Формат отчета (markdown или adoc)");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println("Ошибка при разборе аргументов: " + e.getMessage());
            new HelpFormatter().printHelp(HELP_MESSAGE, options);
            System.exit(1);
        }

        String filePath = cmd.getOptionValue(OPTION_PATH);
        String format = cmd.getOptionValue(OPTION_FORMAT, FORMAT_MARKDOWN);

        if (filePath == null) {
            System.err.println("Не указан путь к файлу лога.");
            new HelpFormatter().printHelp(HELP_MESSAGE, options);
            System.exit(1);
        }

        try {
            List<String> logLines = Files.readAllLines(Paths.get(filePath));
            LogParser logParser = new LogParser();
            List<LogRecord> records = logParser.parse(logLines);

            LogAnalyzer analyzer = new LogAnalyzer();
            LogReport report = analyzer.analyze(records);

            if (FORMAT_MARKDOWN.equals(format)) {
                System.out.println(report.toMarkdown());
            } else {
                System.out.println("Формат отчета '" + format + "' не поддерживается.");
            }

        } catch (Exception e) {
            System.err.println("Ошибка при обработке файла лога: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
