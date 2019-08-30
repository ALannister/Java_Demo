package com.lannister.java.demo.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

//PriorityQueue的数据结构是堆，没有对所有数据进行全部排序
public class PriorityQueueTest {
	public static void main(String[] args) throws NumberFormatException, IOException {
		PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1-o2;
			}
		});
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		while(!"end".equals(str=br.readLine())) {
			queue.offer(Integer.parseInt(str));
			System.out.println(queue);
			if(queue.size()>5) {
				queue.poll();
				System.out.println(queue);
			}
			
		}
	}
}
