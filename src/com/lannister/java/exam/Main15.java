package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main15{
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(br.readLine());
	    String[] strs = br.readLine().split("\\s+");
		int[] arr = new int[n];
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(strs[i]);
		}
		
		int[] left = new int[n];
		int[] right = new int[n];
		for(int i=0;i<n;i++) {
			int tmp = 0;
			int count = 0;
			for(int j=i+1;j<n;j++) {
				if(j == i+1) {
					tmp = arr[j];
					count = 1;
				}
				else {
					if(arr[j] > tmp) {
						tmp = arr[j];
						count += 1;
					}
				}
			}
			right[i] = count;
		}
		for(int i=n-1;i>=0;i--) {
			int tmp = 0;
			int count = 0;
			for(int j=i-1;j>=0;j--) {
				if(j == i-1) {
					tmp = arr[j];
					count = 1;
				}
				else {
					if(arr[j] > tmp) {
						tmp = arr[j];
						count += 1;
					}
				}
			}
			left[i] = count;
		}
		
		for(int i=0;i<n-1;i++) {
			System.out.print(left[i] + right[i] + 1 + " ");
		}
		System.out.println(left[n-1] + right[n-1] + 1);
	}
}