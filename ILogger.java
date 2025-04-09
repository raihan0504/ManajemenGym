// Interface yang dibutuhkan untuk logging
interface ILogger {
    void logInfo(String message);
    void logError(String message, Exception e);
}