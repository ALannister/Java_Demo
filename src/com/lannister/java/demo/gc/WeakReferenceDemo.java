package com.lannister.java.demo.gc;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import org.junit.Test;


//弱引用指向的对象，只要发生GC就会被回收
public class WeakReferenceDemo {
	@Test
	public void enoughMemWithQueue() {
		Object o = new Object();
		ReferenceQueue<Object> queue = new ReferenceQueue<>();
		WeakReference<Object> weak = new WeakReference<Object>(o, queue);
		System.out.println(o);
		System.out.println(weak.get());
		System.out.println(queue.poll());
		
		o = null;
		System.gc();
		System.out.println("==========GC==========");
		//gc线程优先级较低，必须延时一会儿，让gc线程清理完对象后将引用放入队列
		try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
		
		System.out.println(o);
		System.out.println(weak.get());
		System.out.println(queue.poll());
	}
	
	//被回收的对象会被放入ReferenceQueue
	@Test
	//添加VM参数: -Xms10m -Xmx10m -XX:+PrintGCDetails
	public void lackMemWithQueue() {
		Object o = new String("nihao");
		ReferenceQueue<Object> queue = new ReferenceQueue<>();
		WeakReference<Object> weak = new WeakReference<Object>(o, queue);
		System.out.println(o);
		System.out.println(weak.get());
		System.out.println(queue.poll());
		
		o = null;
		try {
			byte[] bs = new byte[20 * 1024 * 1024];
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("==========GC==========");
			System.out.println(o);
			System.out.println(weak.get());
			System.out.println(queue.poll());
		}
	}
}
