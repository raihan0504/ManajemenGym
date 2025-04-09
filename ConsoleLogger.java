
import java.time.LocalDateTime;

// Implementasi sederhana untuk logging
class ConsoleLogger implements ILogger {
    @Override
    public void logInfo(String message) {
        System.out.println("[INFO] " + LocalDateTime.now() + " - " + message);
    }
    
    @Override
    public void logError(String message, Exception e) {
        System.err.println("[ERROR] " + LocalDateTime.now() + " - " + message);
        if (e != null) {
            e.printStackTrace();
        }
    }
}