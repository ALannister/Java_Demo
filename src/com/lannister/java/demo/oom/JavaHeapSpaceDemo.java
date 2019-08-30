package com.lannister.java.demo.oom;

import java.util.UUID;

//Ìí¼ÓVM²ÎÊý: -Xms10m -Xmx10m -XX:+PrintGCDetails
public class JavaHeapSpaceDemo {
	public static void main(String[] args) {
		String str = new String("");
		while(true) {
			str += str + UUID.randomUUID().toString();
			System.out.println("**********" + str.length());
		}
	}
}
