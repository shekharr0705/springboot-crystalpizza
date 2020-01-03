package com.crystalpizaa.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

public class AppLogger {
  private static Logger logger =LoggerFactory.getLogger("com.crystalpizza.api");;

  public static void log(String className,String message, Level level) {
    String logMessage=className+"->"+message;
    switch (level) {
      case INFO:
        logger.info(logMessage);
        break;
      case ERROR:
        logger.error(logMessage);
        break;
      case WARN:
        logger.warn(logMessage);
        break;
      case TRACE:
        logger.trace(logMessage);

    }
  }
}
