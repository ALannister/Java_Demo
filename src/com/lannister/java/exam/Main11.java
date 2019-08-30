package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main11{
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String str = Integer.toBinaryString(Integer.parseInt(br.readLine()));
	    int idx = -1;
	    int count = 0;
		for(int i=str.length()-1;i>=2;i--) {
			if(str.substring(i-2, i+1).equals("101")) {
				if(idx == -1) {
					idx = i;
				}
				count++;
			}
		}
		System.out.println(idx + " " + count);
	}
}