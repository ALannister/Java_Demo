package com.lannister.java.demo.gc;


import org.junit.Test;

//ǿ����ָ��Ķ��󣬼�ʹ�ڴ治��Ҳ���ᱻ����
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
	//���VM����: -Xms10m -Xmx10m -XX:+PrintGCDetails
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
