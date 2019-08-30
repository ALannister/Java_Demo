package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main5{
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(br.readLine());
	    String[] strs = br.readLine().split("\\s+");
		long[] arr = new long[n];
		for(int i=0;i<n;i++) {
			arr[i] = Long.parseLong(strs[i]);
		}
		System.out.println(minAdd(arr));
	}
	public static long minAdd(long[] arr) {
		if(arr.length == 1) return 0L;
		if(arr.length == 2 && arr[0] != arr[1]) return 0L;
		if(arr.length == 2 && arr[0] == arr[1]) return 1;
		long res1 = inc(arr.clone());
		long res2 = desc(arr.clone());
		long res3 = incDesc(arr.clone());
		System.out.println(res1 + " " + res2 + " " + res3);
		return Math.min(res1, Math.min(res2, res3));
	};
	public static long inc(long[] arr) {
		long add = 0;
		for(int i=1;i<arr.length;i++) {
			if(arr[i] <= arr[i-1]) {
				add += arr[i-1] - arr[i] + 1;
				arr[i] = arr[i-1] + 1;
			}
		}
		return add; 
	}
	
	public static long desc(long[] arr) {
		long add = 0;
		for(int i=arr.length-2;i>=0;i--) {
			if(arr[i] <= arr[i+1]) {
				add += arr[i+1] - arr[i] + 1;
				arr[i] = arr[i+1] + 1;
			}
		}
		return add; 
	}
	
	public static long incDesc(long[] arr) {
		int l = 0;
		int h = arr.length-1;
		long add = 0;
		while(l<h-1) {
			if(arr[l] <= arr[h]) {
				if(arr[l+1] <= arr[l]) {
					add += arr[l] - arr[l+1] + 1;
					arr[l+1] = arr[l] + 1;
				}
				l++;
			}
			else {
				if(arr[h-1] <= arr[h]) {
					add += arr[h] - arr[h-1] + 1;
					arr[h-1] = arr[h] + 1;
				}
				h--;
			}
		}
		return add;
	}
}