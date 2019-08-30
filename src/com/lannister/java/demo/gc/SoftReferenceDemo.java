package com.lannister.java.demo.gc;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

import org.junit.Test;

//������ָ��Ķ������ڴ湻�ò��ᱻ���գ����ڴ治���þͻᱻ����
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
	//���VM����: -Xms10m -Xmx10m -XX:+PrintGCDetails
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
	
	//�����յĶ���ᱻ����ReferenceQueue
	@Test
	//���VM����: -Xms10m -Xmx10m -XX:+PrintGCDetails
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
