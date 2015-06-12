package de.alpharogroup.wicket.components.examples;

import lombok.experimental.UtilityClass;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * Logger extensions for appenders.
 */
@UtilityClass
public class LoggerExtensions {

  public static void addFileAppender(Logger logger, FileAppender fileAppender) {
    logger.addAppender(fileAppender);
  }

  public static FileAppender newFileAppender(String logFilePath) {
    FileAppender appender = new FileAppender();
    appender.setName("MyFileAppender");
    appender.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
    appender.setFile(logFilePath);
    appender.setAppend(true);
    appender.setThreshold(Level.DEBUG);
    appender.activateOptions();
    return appender;
  }
}
