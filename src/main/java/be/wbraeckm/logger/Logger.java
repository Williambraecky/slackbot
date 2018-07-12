package be.wbraeckm.logger;

public class Logger {

    public static void log(String message)
    {
        System.out.println(message);
    }

    public static void log(LogType type, String message)
    {
        log(String.format("[%s] %s", type.name(), message));
    }

    public static void info(String message)
    {
        log(LogType.INFO, message);
    }

    public static void error(String message)
    {
        log(LogType.ERROR, message);
    }

    public static void warning(String message)
    {
        log(LogType.WARNING, message);
    }
}
