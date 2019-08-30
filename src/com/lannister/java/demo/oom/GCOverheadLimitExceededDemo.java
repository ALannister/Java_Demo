package com.lannister.java.demo.oom;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

//Ìí¼ÓVM²ÎÊý: -Xms10m -Xmx10m -XX:+PrintGCDetails
public class GCOverheadLimitExceededDemo {
	public static void main(String[] args) {
		List<String> list = new LinkedList<>();
		
		while(true) {
			list.add(UUID.randomUUID().toString());
			System.out.println("*************" + list.size());
		}
	}
}
