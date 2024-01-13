package engine;

import java.util.logging.Logger;

public class Logs {
    private static Logger logger = Logger.getLogger(Logs.class.getName());

    public static Logger getLogger() {
        return logger;
    }
}
