package com.lannister.java.demo.concurrent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//HashMap多线程不安全示例及解决办法
public class UnsafeMapDemo {
	public static void main(String[] args) {
		//Map<Integer, Integer> map = new HashMap<>();//java.util.ConcurrentModificationException
		//Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<>());
		//Map<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();
		Map<Integer, Integer> map = new ConcurrentSkipListMap<Integer, Integer>();
		
		Random random = new Random();
		
		ExecutorService executorService = Executors.newFixedThreadPool(7);
		for(int i = 0; i < 20; i++) {
			executorService.execute(()->{
				map.put(random.nextInt(10),random.nextInt(10));
				System.out.println(map);
			});
		}
		executorService.shutdown();
	}
}
