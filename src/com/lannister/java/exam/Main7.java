package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main7{
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][2];
		for(int i=0;i<n;i++) {
			String[] strs = br.readLine().split("\\s+");
			arr[i][0] = Integer.parseInt(strs[0]);
			arr[i][1] = Integer.parseInt(strs[1]);
		}
		int X = Integer.parseInt(br.readLine());
		String[] strs = br.readLine().split("\\s+");
		int A = Integer.parseInt(strs[0]);
		int B = Integer.parseInt(strs[1]);
		int idx = -1;
		int min = Integer.MAX_VALUE;
		for(int i=0;i<n;i++) {
			int start = arr[i][0] * 60 + arr[i][1] + X;
			int end = A * 60 + B;
			int sub = end - start;
			if(idx == -1) {
				if(sub >= 0) {
					idx = i;
					min = sub;
				}
			}else {
				if(sub >= 0 && sub <= min) {
					idx = i;
					min = sub;
				}
			}
		}
		System.out.println(arr[idx][0] + " " + arr[idx][1]);
	}
}