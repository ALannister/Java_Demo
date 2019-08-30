package com.lannister.java.demo.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

//BlockingQueue���ķ���
public class ArrayBlockingQueueDemo {
	//���쳣
	@Test
	public void test1() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);
		queue.add("a");
		queue.add("b");
	}
	@Test
	public void test2() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);
		queue.remove();
	}
	@Test
	public void test3() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);
		queue.element();
	}
	
	//����ֵ
	@Test
	public void test4() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);
		System.out.println(queue.offer("a"));
		System.out.println(queue.offer("b"));
	}
	@Test
	public void test5() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);
		System.out.println(queue.poll());
	}
	@Test
	public void test6() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);
		System.out.println(queue.peek());
	}
	
	//����
	@Test
	public void test7() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);
		try {
			queue.put("a");
			queue.put("b");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void test8() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);
		try {
			queue.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//��ʱ
	@Test
	public void test9() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);
		try {
			System.out.println(queue.offer("a",1,TimeUnit.SECONDS));
			System.out.println(queue.offer("b",1,TimeUnit.SECONDS));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void test10() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);
		try {
			System.out.println(queue.poll(1,TimeUnit.SECONDS));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
