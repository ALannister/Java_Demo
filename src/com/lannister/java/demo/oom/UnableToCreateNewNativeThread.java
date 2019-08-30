package com.lannister.java.demo.oom;

import java.util.concurrent.CountDownLatch;

//添加VM参数: -Xss100m -Xms7168m -Xmx7168m -XX:+PrintGCDetails
public class UnableToCreateNewNativeThread {
	public static void main(String[] args) {
		int i = 0;
		
		while(true) {
			try {
				new Thread(new HoldThread()).start();
				i++;
				//System.out.println("************已经创建线程数：" + i);
			} catch (Throwable e) {
				
				e.printStackTrace();
				System.out.println("************已经创建线程数：" + i);
			}
		}
	}
}

class HoldThread extends Thread{
	CountDownLatch cdl = new CountDownLatch(1);
	
	public HoldThread() {
		this.setDaemon(true);
	}
	
	public void run() {
		try {
			cdl.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 }