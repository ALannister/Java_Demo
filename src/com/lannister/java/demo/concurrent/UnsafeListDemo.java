package com.lannister.java.demo.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;


//ArrayList多线程不安全示例及解决办法
public class UnsafeListDemo {
	public static void main(String[] args) {
		//List<Integer> list = new ArrayList<Integer>();// java.util.ConcurrentModificationException
		//List<Integer> list = Collections.synchronizedList(new ArrayList<>());//synchronized方法
		List<Integer> list = new CopyOnWriteArrayList<Integer>();//读写分离，写时复制,lock
		//List<Integer> list = new Vector<Integer>();//synchronized方法
		
		
		Random random = new Random();
		
		ExecutorService executorService = Executors.newFixedThreadPool(7);
		for(int i = 0; i < 20; i++) {
			executorService.execute(()->{
				list.add(random.nextInt(10));
				System.out.println(list);
			});
		}
		executorService.shutdown();
	}
	
	//即使是线程安全的集合，如果使用get()方法将元素取出修改
	//也会出现问题
	@Test
	public void test() throws InterruptedException{
		//List<Data1> list = new CopyOnWriteArrayList<>();
		List<Data1> list = new Vector<>();
		
		list.add(new Data1());
		list.add(new Data1());
		list.add(new Data1());
		
		Thread t1 = new Thread(()->{
			for(int i = 0; i<10000;i++){
				Data1 data1 = list.get(0);
				data1.a += 1;
			}
		});
		Thread t2 = new Thread(()->{
			for(int i = 0; i<10000;i++){
				Data1 data1 = list.get(0);
				data1.a += 1;
			}
		});
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(list);
	}
}
