package com.example.demo.util;

import java.util.logging.*;

public class InfoLogger {
	/**
	 * @Description: 创建一个记录器名为com.lanting.test的自定义日志（这个记录器名有层级关系，例如给记录器名为com.lanting的自定义日志设置属性，也会影响到com.lanting.test）
	 */
	private static final Logger logger = Logger.getLogger("com.lanting.test");

	public InfoLogger() {

		// 可选，设置打印日志级别(默认打印Info级别及以上的信息)
		logger.setLevel(Level.INFO);

		// 打印info级别的日志
		logger.info("测试info日志");
		// 打印warning级别的日志
		logger.warning("测试warning日志");

		// 也可以这样打印日志
		logger.log(Level.INFO, "Logger init success");

	}
}
