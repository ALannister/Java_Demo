package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main9{
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(br.readLine());
	    String[] strs = br.readLine().split("\\s+");
		int[] year = new int[n];
		int[] money = new int[n];
		for(int i=0;i<n;i++) {
			year[i] = Integer.parseInt(strs[i]);
			money[i] = 100;
		}
		int count = 1;
		while(count > 0) {
			count = 0;
			for(int i=0;i<n-1;i++) {
				if(year[i] < year[i+1] && money[i+1] <= money[i]) {
					money[i+1] = money[i] + 100;
					continue;
				}
				if(year[i] > year[i+1] && money[i+1] >= money[i]) {
					money[i] = money[i+1] + 100;
					continue;
				}
			}
		}
		int sum = 0;
		for(int i=0;i<n;i++) {
			sum += money[i];
		}
		System.out.println(sum);
		
	}
}