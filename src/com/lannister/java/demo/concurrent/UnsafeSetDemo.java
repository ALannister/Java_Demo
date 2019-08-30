package com.lannister.java.demo.concurrent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//HashSet多线程不安全示例及解决办法
public class UnsafeSetDemo {
	public static void main(String[] args) {
		//Set<Integer> set = new HashSet<Integer>();//java.util.ConcurrentModificationException
		//Set<Integer> set = Collections.synchronizedSet(new HashSet<>());//synchronized方法
		Set<Integer> set = new CopyOnWriteArraySet<Integer>();//读写分离，写时复制,lock
		//Set<Integer> set = new ConcurrentSkipListSet<Integer>();
		
		Random random = new Random();
		
		ExecutorService executorService = Executors.newFixedThreadPool(7);
		
		for(int i = 0; i < 20; i++) {
			executorService.execute(()->{
				set.add(random.nextInt(10));
				System.out.println(set);
			});
		}
		executorService.shutdown();
	}
}