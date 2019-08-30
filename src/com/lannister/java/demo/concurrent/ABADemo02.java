package com.lannister.java.demo.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

import com.sun.swing.internal.plaf.basic.resources.basic;

//thread1: A(stamp1) ---------------------------------------> C(stamp2)
//thread2:               A(stamp1)->B(stamp2)->A(stamp3)
public class ABADemo02 {
	public static void main(String[] args) {
		AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(0, 0);
		System.out.println("Thread" + "\t" + "value" + "\t" + "stamp");
		
		new Thread(()->{
			int A = atomicStampedReference.getReference();
			int B = 100;
			
			try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
			
			System.out.println(Thread.currentThread().getName() + "\t" + atomicStampedReference.getReference() + "\t" + atomicStampedReference.getStamp());
			atomicStampedReference.compareAndSet(A, B, atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1);
			System.out.println(Thread.currentThread().getName() + "\t" + atomicStampedReference.getReference() + "\t" + atomicStampedReference.getStamp());
			atomicStampedReference.compareAndSet(B, A, atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
			System.out.println(Thread.currentThread().getName() + "\t" + atomicStampedReference.getReference() + "\t" + atomicStampedReference.getStamp());
			
		}, "AAA").start();
		
		new Thread(()->{
			int A = atomicStampedReference.getReference();
			int C = 200;
			int stamp = atomicStampedReference.getStamp();
			
			try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}
			
			System.out.println(Thread.currentThread().getName() + "\t" + atomicStampedReference.getReference() + "\t" + atomicStampedReference.getStamp());
			atomicStampedReference.compareAndSet(A, C, stamp, stamp + 1);
			System.out.println(Thread.currentThread().getName() + "\t" + atomicStampedReference.getReference() + "\t" + atomicStampedReference.getStamp());
			
		},"BBB").start();
	}
}
