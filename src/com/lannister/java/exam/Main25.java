package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
//Æ½·½ºÍ
public class Main25{
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int m = Integer.parseInt(br.readLine());
	    for(int i=0;i<m;i++) {
	    	solution(Integer.parseInt(br.readLine()));
	    }
	}
	public static void solution(int num) {
		int limit = 99;
		while(limit > 0) {
			ArrayList<Integer> list = split(num);
			num = 0;
			for(Integer n : list) {
				num += Math.pow(n,2);
			}
			if(num == 1) {
				System.out.println("true");
				return;
			}
			limit--;
		}
		System.out.println("false");
	}
	public static ArrayList<Integer> split(int num){
		ArrayList<Integer> list = new ArrayList<>();
		do {
			int tmp = num % 10;
			list.add(tmp);
			num = num/10;
		}while(num > 0);
		return list;
	}
}