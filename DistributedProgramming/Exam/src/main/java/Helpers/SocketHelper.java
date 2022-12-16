package Helpers;

public class SocketHelper {
    public static String generateLineWithSeparator(String... args) {
        StringBuilder line = new StringBuilder();
        for (String arg : args) {
            line.append(arg).append("%");
        }
        return line.toString();
    }

    public static String[] splitQuery(String query) {
        return query.split("%");
    }
}
