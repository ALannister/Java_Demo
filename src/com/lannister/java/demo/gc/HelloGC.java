package com.lannister.java.demo.gc;

import java.util.Random;

//-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC        ===>   Serial GC+Serial Old GC                ===>   DefNew+Tenured   
//-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC        ===>   ParNew GC+Serial Old GC                ===>   ParNew+Tenured   
//-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC      ===>   Parallel Scavenge GC+Parallel Old GC   ===>   PSYoungGen+ParOldGen
//-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC   ===>   Parallel Scavenge GC+Parallel Old GC   ===>   PSYoungGen+ParOldGen
//-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC ===>   ParNew GC+CMS GC                       ===>   ParNew+CMS
//-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC            ===>   G1 GC                  
//-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialOldGC  
//Serial Old GC 已经被优化掉了，没有了：
//Unrecognized VM option 'UseSerialOldGC'
//Did you mean '(+/-)UseSerialGC'?
public class HelloGC {
	public static void main(String[] args) {
		System.out.println("********Hellol GC!");
		try {
			String str = new String();
			while(true) {
				str += str + new Random().nextInt(11111111) + new Random().nextInt(9999999);
				str.intern();
			}
		} catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
