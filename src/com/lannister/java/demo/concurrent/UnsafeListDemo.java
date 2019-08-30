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


//ArrayList���̲߳���ȫʾ��������취
public class UnsafeListDemo {
	public static void main(String[] args) {
		//List<Integer> list = new ArrayList<Integer>();// java.util.ConcurrentModificationException
		//List<Integer> list = Collections.synchronizedList(new ArrayList<>());//synchronized����
		List<Integer> list = new CopyOnWriteArrayList<Integer>();//��д���룬дʱ����,lock
		//List<Integer> list = new Vector<Integer>();//synchronized����
		
		
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
	
	//��ʹ���̰߳�ȫ�ļ��ϣ����ʹ��get()������Ԫ��ȡ���޸�
	//Ҳ���������
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
