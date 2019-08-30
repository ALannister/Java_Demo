package com.lannister.java.demo.gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class PhantomReferenceDemo {
		//�޷�ͨ�������û�ö���phantom.get()��Զ����null
		@Test
		public void EnoughMemWithQueue() {
			Object o = new Object();
			ReferenceQueue<Object> queue = new ReferenceQueue<>();
			PhantomReference<Object> phantom = new PhantomReference<Object>(o, queue);
			System.out.println(o);
			System.out.println(phantom.get());
			System.out.println(queue.poll());
			
			o = null;
			System.gc();
			System.out.println("==========GC==========");

			System.out.println(o);
			System.out.println(phantom.get());
			System.out.println(queue.poll());
		}
}
