package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main17{
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strs = br.readLine().split("\\s+");
		int n = Integer.parseInt(strs[0]);
		int L = Integer.parseInt(strs[1]);
	    String[] strs1 = br.readLine().split("\\s+");
	    String[] strs2 = br.readLine().split("\\s+");
		int[] x = new int[n];
		int[] y = new int[n];
		for(int i=0;i<n;i++) {
			x[i] = Integer.parseInt(strs1[i]);
			y[i] = Integer.parseInt(strs2[i]);
		}
		System.out.println(-1);
	}
}