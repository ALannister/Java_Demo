package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main10{
	static int[] lengthSum = new int[3];
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(br.readLine());
		int[][] path = new int[n+1][n+1];
		for(int i=0;i<n-1;i++) {
			String[] strs = br.readLine().split("\\s+");
			int A = Integer.parseInt(strs[0]);
			int B = Integer.parseInt(strs[1]);
			int min = Math.min(A, B);
			int max = Math.max(A, B);
			path[min][max] = 1;
		}
		for(int i=1;i<n;i++) {
			findPath(path,i,0);
		}
		System.out.println(lengthSum[0] + " " + lengthSum[1] + " " + lengthSum[2]);
	}
	
	public static void findPath(int[][] path,int start,int length) {
		for(int i=start+1;i<path.length;i++) {
			if(path[start][start+1] == 1) {
				int tmp = (length+1) % 3;
				lengthSum[tmp] += length + 1;
				lengthSum[tmp] = lengthSum[tmp]% 1000000007;
				findPath(path,start+1,length+1);
			} 
		}
	}
}