package com.lannister.java.demo.test;


//用于模拟CPU占用过高
public class CpuHigh {
	public static void main(String[] args) {
		int i = 0;
		while(true) {
			System.out.println(i++);
		}
	}
}
