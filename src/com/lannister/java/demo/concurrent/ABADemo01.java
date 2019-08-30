package com.lannister.java.demo.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

//thread1: A --------------------> C
//thread2:         A->B->A
public class ABADemo01 {
	public static void main(String[] args) {
		AtomicReference<Integer> atomicReference = new AtomicReference<Integer>(0);
		
		new Thread(()->{
			int A = atomicReference.get();
			int B = 100;
			
			try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
			
			boolean a = atomicReference.compareAndSet(A, B);
			System.out.println(Thread.currentThread().getName() + " " +  A + " -> " + B + " :" + a);
			boolean b = atomicReference.compareAndSet(B, A);
			System.out.println(Thread.currentThread().getName() + " " + B + " -> " + A + " :" + b);
		}).start();
		
		new Thread(()->{
			int A = atomicReference.get();
			int C = 200;
			
			try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}
			
			boolean c = atomicReference.compareAndSet(A, C);
			System.out.println(Thread.currentThread().getName() + " " + A + " -> " + C + " :" + c);
		}).start();
	}
}
