package com.lannister.java.demo.jvm;

import org.junit.Test;

public class PrintFlagsDemo {
	@Test
	public void test1() {
    	double Xms = Runtime.getRuntime().totalMemory();
		double Xmx = Runtime.getRuntime().maxMemory();
		System.out.println("-Xms = " + Xms/1024.0/1024.0 + "m");
		System.out.println("-Xmx = " + Xmx/1024.0/1024.0 + "m");
		System.out.println("启动类加载器加载目录： " + System.getProperty("sun.boot.class.path"));
		System.out.println("扩展类加载器加载目录：" + System.getProperty("java.ext.dirs"));
		System.out.println("应用程序加载器加载目录：" + System.getProperty("java.class.path"));
	}
}
