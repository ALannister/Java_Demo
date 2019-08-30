package com.lannister.java.demo.oom;

import java.nio.ByteBuffer;

//Ìí¼ÓVM²ÎÊý: -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
public class DirectBufferMemoryDemo {
	public static void main(String[] args) {
		System.out.println("Max Direct Memory: " + sun.misc.VM.maxDirectMemory() / 1024.0 / 1024.0 + " MB");
		
		ByteBuffer buffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);
		
	}
}
