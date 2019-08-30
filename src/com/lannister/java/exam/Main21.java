package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//���� �ϳ���
public class Main21{
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(br.readLine());
	    String[] strs = br.readLine().split("\\s+");
		int[] arr = new int[n];
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(strs[i]);
		}
		int res = group(arr,0);
		System.out.println(res);
	}
	public static int group(int[] arr,int idx) {
		if(idx >= arr.length) return 0;
		int flag = -1;
		for(int i=idx+1;i<arr.length;i++) {
			if(arr[i] < arr[idx]) flag = i;
		}
		if(flag == -1) return 1+group(arr, idx+1);
		else return 1+group(arr, flag+1);
	}
}
