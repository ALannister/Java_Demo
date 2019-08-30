package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main19{
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String[] strs = br.readLine().split(",");
		for(int i=0;i<strs.length;i++) {
			minPre(strs,i,new int[strs.length]);
			if(i<strs.length-1) System.out.print(",");
		}
		System.out.println();
	}
	public static void minPre(String[] strs,int idx,int[] flag) {
		for(int i=0;i<strs[idx].length();i++) {
			char ch = strs[idx].charAt(i);
			for(int j=0;j<strs.length;j++) {
				if(flag[j] == 1) continue;
				else {
					if(i<strs[j].length()) {
						if(strs[j].charAt(i) != ch) flag[j] = 1;
					}
					else {
						flag[j] = 1;
					}
					
				}
			}
			int count = 0;
			for(int k=0;k<flag.length;k++) {
				count += flag[k];
			}
			if(count == strs.length - 1) {
				System.out.print(strs[idx].substring(0,i+1));
				return;
			}
				
		}
		System.out.println(strs[idx]);
		
	}
}