package com.lannister.java.demo.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

//CAS≤‚ ‘
public class CASDemo {
	public static void main(String[] args) {
		
		AtomicInteger atomicInteger= new AtomicInteger();
		boolean a = atomicInteger.compareAndSet(0, 10);
		System.out.println(a);
		boolean b = atomicInteger.compareAndSet(0, 10);
		System.out.println(b);
		boolean c = atomicInteger.compareAndSet(10, 20);
		System.out.println(c);
		System.out.println(atomicInteger.get());
	}
}
