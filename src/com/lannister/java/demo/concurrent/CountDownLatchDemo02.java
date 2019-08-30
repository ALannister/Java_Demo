package com.lannister.java.demo.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//ͨ��CountDownLatch��CyclicBarrier����̵߳���
public class CountDownLatchDemo02 {
	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(12);
		CountDownLatch countDownLatch2 = new CountDownLatch(12);
		CyclicBarrier cyclicBarrier = new CyclicBarrier(13);
		
		ExecutorService executorService = Executors.newFixedThreadPool(12);
		for(int i = 1; i <= 12; i++) {
			final int tmpInt = i;
			executorService.execute(()->{
				
				System.out.println(Member.getMember(tmpInt).getName() + " �뿪����");
				countDownLatch.countDown();
				
				try {
					cyclicBarrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println(Member.getMember(tmpInt).getName() + " �뿪��ѧ¥");
				countDownLatch2.countDown();
			});
		}
		
		ExecutorService executorService2 = Executors.newSingleThreadExecutor();
		executorService2.execute(()->{
			try {
				countDownLatch.await();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("�೤���Ͻ�����");
			
			
			try {
				cyclicBarrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				countDownLatch2.await();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("�೤���Ͻ�ѧ¥��");
		});
		
		//�ر��̳߳�
		try {
			executorService.awaitTermination(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executorService.shutdown();
		
		try {
			executorService2.awaitTermination(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executorService2.shutdown();
	}
}

enum Member{
	
	A(1,"��"),B(2,"ţ"),C(3,"��"),D(4,"��"),E(5,"��"),F(6,"��"),G(7,"��"),H(8,"��"),
	I(9,"��"),J(10,"��"),K(11,"��"),L(12,"��");
	
	private final int num;
	private final String name;
	public int getNum() {
		return num;
	}

	public String getName() {
		return name;
	}

	
	
	private Member(int num, String name) {
		this.num = num;
		this.name = name;
	}

	public static Member getMember(int num) {
		Member[] members = Member.values();
		for (Member member : members) {
			if(member.num == num) {
				return member;
			}
		}
		return null;
	}
	
}