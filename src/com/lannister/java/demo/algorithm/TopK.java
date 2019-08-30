package com.lannister.java.demo.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

//输入每行一个单词，统计出现频次top k的单词
public class TopK {
	public static final int K = 5;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<String, Integer> map = new HashMap<>();
		String str;
		while(!"end".equals((str = br.readLine()))) {
			if(map.containsKey(str)) {
				int value = map.get(str);
				map.replace(str, value + 1);
			}else {
				map.put(str, 1);
			}
		}
		System.out.println(map);
		PriorityQueue<HashMap.Entry<String, Integer>> queue = new PriorityQueue<>(K, new Comparator<HashMap.Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				// TODO Auto-generated method stub
				return o1.getValue()-o2.getValue();
			}
		});
		for(HashMap.Entry<String,Integer> entry : map.entrySet()) {
			queue.offer(entry);
			if(queue.size() > K) {
				queue.poll();
			}
		}
		System.out.println(queue);
 	}
}
