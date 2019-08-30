package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main20{
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    char[] chs = br.readLine().toCharArray();
	    int[][] flag = new int[26][2];
		for(int i=0;i<chs.length;i++) {
			if(flag[chs[i]-'A'][0] == 0) {
				flag[chs[i]-'A'][0] = i;
				flag[chs[i]-'A'][1] = i;
			}else {
				flag[chs[i]-'A'][1] = i;
			}
		}
		int end = 0;
		int start = 0;
		do {
			end = get(chs,start,flag);
			System.out.print(end-start+1);
            start = end+1;
			if(end < chs.length-1) {
				System.out.print(" ");
			}
		}while(end < chs.length);
		System.out.println();
	}
	
	public static int get(char[] chs,int start,int[][] flag) {
		int end = flag[chs[start]-'A'][1];
		for(int i=start+1;i<end;i++) {
			if(flag[chs[i]-'A'][1] > end)
				end = flag[chs[i]-'A'][1];
		}
		
		return end;
	}
}