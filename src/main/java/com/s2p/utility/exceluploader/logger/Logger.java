package com.s2p.utility.exceluploader.logger;

import java.util.logging.Level;

public class Logger {
    private Logger() {

    }
    private static Logger logger = new Logger();

    public static Logger getLogger() {
        return logger;
    }

    public void log(Level level, String message) {
        if (level == Level.INFO) {
            System.out.println(message);
        }
        // handle others
    }

    public void log(Level level, Object object) {
        if (object == null) {
            log(level, "Null object is logged here");
        }
        log(level, object.toString());
    }

    public void info(Object object) {
        log(Level.INFO, object);
    }
}
