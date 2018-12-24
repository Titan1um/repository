package com.example.demo.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.*;

@Component
public class InfoLogger {
	/**
	 * @Description: 创建一个记录器名为com.lanting.test的自定义日志（这个记录器名有层级关系，例如给记录器名为com.lanting的自定义日志设置属性，也会影响到com.lanting.test）
	 */
	private static final Logger logger = Logger.getLogger("com.lanting.test");


	public InfoLogger() throws IOException {

		//日志处理器,设置追加模式
		FileHandler fileHandler = new FileHandler("log.txt",true);
		//需要记录的日志消息
		LogRecord lr = new LogRecord(Level.INFO, "This is a text log.");
		//为处理器设置日志格式：Formatter
		SimpleFormatter sf = new SimpleFormatter();
		fileHandler.setFormatter(sf);
		//注册处理器
		logger.addHandler(fileHandler);
		//记录日志消息,用logRecord方式
//		logger.log(lr);
		// 也可以这样打印日志C:/
//		logger.log(Level.INFO, "Logger init success");
		// 打印info级别的日志
		logger.info("looger init success.");
		// 打印warning级别的日志
//		logger.warning("测试warning日志");

	}

	public void log(String msg) {
		logger.info(msg);
	}

	//test
	public static void main(String[] args) {
		try {
			new InfoLogger().log("hi,mtfk");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
