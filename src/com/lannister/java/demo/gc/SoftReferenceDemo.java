package com.lannister.java.demo.gc;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

import org.junit.Test;

//软引用指向的对象，若内存够用不会被回收，若内存不够用就会被回收
public class SoftReferenceDemo {
	@Test
	public void enoughMem() {
		Object o = new Object();
		SoftReference<Object> soft = new SoftReference<Object>(o);
		System.out.println(o);
		System.out.println(soft.get());
		
		o = null;
		System.gc();
		System.out.println("==========GC==========");
		
		System.out.println(o);
		System.out.println(soft.get());
	}
	
	@Test
	//添加VM参数: -Xms10m -Xmx10m -XX:+PrintGCDetails
	public void lackMem() {
		Object o = new Object();
		SoftReference<Object> soft = new SoftReference<Object>(o);
		System.out.println(o);
		System.out.println(soft.get());
		
		o = null;
		try {
			byte[] bs = new byte[20 * 1024 * 1024];
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("==========GC==========");
			System.out.println(o);
			System.out.println(soft.get());
		}
	}
	
	//被回收的对象会被放入ReferenceQueue
	@Test
	//添加VM参数: -Xms10m -Xmx10m -XX:+PrintGCDetails
	public void lackMemWithQueue() {
		Object o = new String("apple");
		ReferenceQueue<Object> queue = new ReferenceQueue<>();
		SoftReference<Object> soft = new SoftReference<Object>(o, queue);
		System.out.println(o);
		System.out.println(soft.get());
		System.out.println(queue.poll());
		
		o = null;
		try {
			byte[] bs = new byte[20 * 1024 * 1024];
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("==========GC==========");
			System.out.println(o);
			System.out.println(soft.get());
			SoftReference<String> s = (SoftReference<String>) queue.poll();
			System.out.println(s);
			System.out.println(s.get());
		}
	}
}
