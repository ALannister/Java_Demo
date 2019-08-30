package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main4{
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(br.readLine());
	    String[] strs = br.readLine().split("\\s+");
		int[] arr = new int[n];
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(strs[i]);
		}
		int count = 0;
		for(int i=0;i<n;i++) {
			for(int j=i+1;j<n;j++) {
				if(arr[j] > arr[i] && arr[i] >= 0.9*arr[j]) {
					count++;
					continue;
				}
				if(arr[i] > arr[j] && arr[j] >= 0.9*arr[i]) {
					count++;
					continue;
				}
			}
		}
		System.out.println(count);
	}
}