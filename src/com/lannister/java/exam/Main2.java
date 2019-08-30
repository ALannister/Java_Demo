package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2{
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(br.readLine());
	    String[] strs = br.readLine().split("\\s+");
		long[] arr = new long[n];
		for(int i=0;i<n;i++) {
			arr[i] = Long.parseLong(strs[i]);
		}
		long x1 = arr[0];
		long x2 = arr[1];
		long sub = Math.abs(x1-x2);
		for(int i=2;i<n;i++) {
			if(Math.abs(arr[i] - arr[i-1]) < sub) {
				x1 = arr[i-1];
				x2 = arr[i];
				sub = Math.abs(arr[i] - arr[i-1]);
			}
		}
		System.out.println(x1+" "+x2);
		
	}
}