package com.lannister.java.demo.gc;


import org.junit.Test;

//强引用指向的对象，即使内存不够也不会被回收
public class StrongReferenceDemo {
	
	@Test
	public void enoughMem() {
		Object o1 = new Object();
		Object o2 = o1;
		System.out.println(o1);
		System.out.println(o2);
		
		o1 = null;
		System.gc();
		System.out.println("==========GC==========");
		
		System.out.println(o1);
		System.out.println(o2);
	}
	
	@Test
	//添加VM参数: -Xms10m -Xmx10m -XX:+PrintGCDetails
	public void lackMem() {
		Object o1 = new Object();
		Object o2 = o1;
		System.out.println(o1);
		System.out.println(o2);
		
		o1 = null;
		try {
			byte[] bs = new byte[20 * 1024 * 1024];
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("==========GC==========");
			System.out.println(o1);
			System.out.println(o2);
		}
		
	}
}
