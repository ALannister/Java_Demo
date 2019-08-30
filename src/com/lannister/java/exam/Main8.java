package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main8{
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strs = br.readLine().split("\\s+");
		int N = Integer.parseInt(strs[0]);
		int K = Integer.parseInt(strs[1]);
	    String str = br.readLine();
		int[] S = new int[N+K-1];
		int[] B = new int[N];
		for(int i=0;i<N+K-1;i++) {
			if(str.charAt(i) == '1') S[i] = 1;
		}
		B[0] = S[0];
		for(int i=1;i<N;i++) {
			int tmp = 0;
			for(int j=i;j>=0 && i-j<K;j--) {
				tmp ^= B[j];
			}
			if(tmp != S[i]) B[i] = 1;
		}
		for(int i=0;i<N;i++) {
			System.out.print(B[i]);
		}
		System.out.println("");
	}
}