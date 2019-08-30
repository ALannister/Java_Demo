package com.lannister.java.demo.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//通过CountDownLatch和CyclicBarrier完成线程调度
public class CountDownLatchDemo02 {
	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(12);
		CountDownLatch countDownLatch2 = new CountDownLatch(12);
		CyclicBarrier cyclicBarrier = new CyclicBarrier(13);
		
		ExecutorService executorService = Executors.newFixedThreadPool(12);
		for(int i = 1; i <= 12; i++) {
			final int tmpInt = i;
			executorService.execute(()->{
				
				System.out.println(Member.getMember(tmpInt).getName() + " 离开教室");
				countDownLatch.countDown();
				
				try {
					cyclicBarrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println(Member.getMember(tmpInt).getName() + " 离开教学楼");
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
			System.out.println("班长锁上教室门");
			
			
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
			System.out.println("班长锁上教学楼门");
		});
		
		//关闭线程池
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
	
	A(1,"鼠"),B(2,"牛"),C(3,"虎"),D(4,"兔"),E(5,"龙"),F(6,"蛇"),G(7,"马"),H(8,"羊"),
	I(9,"猴"),J(10,"鸡"),K(11,"狗"),L(12,"猪");
	
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