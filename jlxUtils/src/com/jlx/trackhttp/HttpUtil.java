package com.jlx.trackhttp;

import java.io.FileOutputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * trackhttp request,print all info
 * 
 * @author Administrator
 */
public class HttpUtil {

	public static void main(String[] args) {

		System.setProperty("org.apache.commons.logging.Log",
				"org.apache.commons.logging.impl.SimpleLog");

		System.setProperty("org.apache.commons.logging.simplelog.showdatetime",
				"true");

		System
				.setProperty(
						"org.apache.commons.logging.simplelog.log.httpclient.wire.header",
						"debug");

		System
				.setProperty(
						"org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient",
						"debug");

//		System.setProperty(
//				"org.apache.commons.logging.simplelog.log.httpclient.wire",
//				"debug");

		String strUrl = "http://kportal.js.cmcc/amserver/UI/Login?module=LDAP&IDToken1=chengwz&IDToken2=password";
		HttpClient httpclient = new HttpClient();
		GetMethod httpGet = new GetMethod(strUrl);
		try {
			int result = httpclient.executeMethod(httpGet);
			System.out.println("rtn code:" + result);
			FileOutputStream fout = new FileOutputStream(
					"c://httpclientRtn.txt");
			byte[] ss = httpGet.getResponseBodyAsString().getBytes();
			fout.write(ss);
			// IOUtil.copy(httpGet.getResponseBodyAsStream(),fout);
			fout.flush();
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpGet.releaseConnection();
	}
}

/*
 * see behind at httpclient javadoc Commons Logging Examples Commons Logging
 * comes with a basic logger called SimpleLog. This logger writes all logged
 * messages to System.err. The following examples show how to configure Commons
 * Logging via system properties to use SimpleLog. Note: The system properties
 * must be set before a reference to any Commons Logging class is made.
 * ===========================================================================
 * Enable * header * wire + context * logging - Best for Debugging
 * ===========================================================================
 * System.setProperty("org.apache.commons.logging.Log",
 * "org.apache.commons.logging.impl.SimpleLog");
 * System.setProperty("org.apache.commons.logging.simplelog.showdatetime",
 * "true");
 * System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire.header",
 * "debug");
 * System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient",
 * "debug");
 * ===========================================================================
 * Enable full wire(header and content) + context logging
 * ===========================================================================
 * System.setProperty("org.apache.commons.logging.Log",
 * "org.apache.commons.logging.impl.SimpleLog");
 * System.setProperty("org.apache.commons.logging.simplelog.showdatetime",
 * "true");
 * System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire",
 * "debug");
 * System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient",
 * "debug");
 * ===========================================================================
 * Enable just context logging
 * =========================================================
 * System.setProperty("org.apache.commons.logging.Log",
 * "org.apache.commons.logging.impl.SimpleLog");
 * System.setProperty("org.apache.commons.logging.simplelog.showdatetime",
 * "true");
 * System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient",
 * "debug");
 * ===========================================================================
 * Log4j Examples
 * ===========================================================================
 * The simplest way to configure Log4j is via a * log4j.properties file. Log4j
 * will automatically read and configure itself * using a file named
 * log4j.properties when it's present at the root of the application classpath.
 * Below are some Log4j configuration examples. Note: Log4j is not included in
 * the HttpClient distribution.
 * ===========================================================================
 * Enable * header wire + * context logging - Best for Debugging
 * ===========================================================================
 * log4j.rootLogger=INFO, stdout
 * log4j.appender.stdout=org.apache.log4j.ConsoleAppender
 * log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
 * log4j.appender.stdout.layout.ConversionPattern=%5p [%c] %m%n
 * log4j.logger.httpclient.wire.header=DEBUG
 * log4j.logger.org.apache.commons.httpclient=DEBUG
 * ===========================================================================
 * Enable full wire(header and * content) + context * logging
 * ===========================================================================
 * log4j.rootLogger=INFO, stdout
 * log4j.appender.stdout=org.apache.log4j.ConsoleAppender
 * log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
 * log4j.appender.stdout.layout.ConversionPattern=%5p [%c] %m%n
 * log4j.logger.httpclient.wire=DEBUG
 * log4j.logger.org.apache.commons.httpclient=DEBUG
 * ===========================================================================
 * Log wire to file + context logging log4j.rootLogger=INFO
 * log4j.appender.stdout=org.apache.log4j.ConsoleAppender
 * log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
 * log4j.appender.stdout.layout.ConversionPattern=%5p [%c] %m%n
 * log4j.appender.F=org.apache.log4j.FileAppender log4j.appender.F.File=wire.log
 * log4j.appender.F.layout=org.apache.log4j.PatternLayout
 * log4j.appender.F.layout.ConversionPattern =%5p [%c] %m%n
 * log4j.logger.httpclient.wire=DEBUG, F
 * log4j.logger.org.apache.commons.httpclient=DEBUG, stdout
 * ===========================================================================
 * Enable * just context
 * ===========================================================================
 * logging log4j.rootLogger=INFO, stdout
 * log4j.appender.stdout=org.apache.log4j.ConsoleAppender
 * log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
 * log4j.appender.stdout.layout.ConversionPattern=%5p [%c] %m%n
 * log4j.logger.org.apache.commons.httpclient=DEBUG
 * ===========================================================================
 * Note that the default configuration for Log4J is very inefficient as it
 * causes all the logging information to be generated but not actually sent
 * anywhere. The Log4J manual is the best reference for how to configure Log4J.
 * It is available at http://logging.apache.org/log4j/docs/manual.html
 * java.util.logging Examples Since JDK 1.4 there has been a package
 * java.util.logging that provides a logging framework similar to Log4J. By
 * default it reads a config file from $JAVA_HOME/jre/lib/logging.properties
 * which looks like this (comments stripped):
 * handlers=java.util.logging.ConsoleHandler .level=INFO
 * java.util.logging.FileHandler.pattern = %h/java%u.log
 * java.util.logging.FileHandler.limit = 50000
 * java.util.logging.FileHandler.count = 1
 * java.util.logging.FileHandler.formatter = java.util.logging.XMLFormatter
 * java.util.logging.ConsoleHandler.level = INFO
 * java.util.logging.ConsoleHandler.formatter =
 * java.util.logging.SimpleFormatter com.xyz.foo.level = SEVERE To customize
 * logging a custom logging.properties file should be created in the project
 * directory. The location of this file must be passed to the JVM as a system
 * property. This can be done on the command line like so: $JAVA_HOME/java
 * -Djava.util.logging.config.file=$HOME/myapp/logging.properties -classpath
 * $HOME/myapp/target/classes com.myapp.Main Alternatively
 * LogManager#readConfiguration(InputStream) can be used to pass it the desired
 * configuration. Enable header wire + context logging - Best for Debugging
 * .level=INFO handlers=java.util.logging.ConsoleHandler
 * java.util.logging.ConsoleHandler.formatter =
 * java.util.logging.SimpleFormatter httpclient.wire.header.level=FINEST
 * org.apache.commons.httpclient.level=FINEST Enable full wire(header and
 * content) + context logging .level=INFO
 * handlers=java.util.logging.ConsoleHandler
 * java.util.logging.ConsoleHandler.formatter =
 * java.util.logging.SimpleFormatter httpclient.wire.level=FINEST
 * org.apache.commons.httpclient.level=FINEST Enable just context logging
 * .level=INFO handlers=java.util.logging.ConsoleHandler
 * java.util.logging.ConsoleHandler.formatter =
 * java.util.logging.SimpleFormatter org.apache.commons.httpclient.level=FINEST
 */