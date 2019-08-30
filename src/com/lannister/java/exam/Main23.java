package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//�汾��
public class Main23{
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int m = Integer.parseInt(br.readLine());
	    for(int i=0;i<m;i++) {
	    	String[] strs = br.readLine().split("\\s+");
	    	update(strs[0],strs[1]);
	    }
	}
	public static void update(String str1,String str2) {
		String[] strs1 = str1.split("\\.");
		String[] strs2 = str2.split("\\.");
		int[] x1 = new int[4];
		int[] x2 = new int[4];
		for(int i=0;i<strs1.length;i++) {
			x1[i] = Integer.parseInt(strs1[i]);
		}
		for(int i=0;i<strs2.length;i++) {
			x2[i] = Integer.parseInt(strs2[i]);
		}
		for(int i=0;i<x1.length;i++) {
			if(x1[i] < x2[i]) {
				System.out.println("true");
				return;
			}
			else if(x1[i] > x2[i]){
				System.out.println("false");
				return;
			}
				
		}
		System.out.println("false");
		
	}
}