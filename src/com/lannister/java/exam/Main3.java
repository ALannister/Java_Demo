package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main3{
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		System.out.println(maxSquence(arr));
		
	}
	public static int maxSquence(int[] arr){
		int res[] = new int[arr.length];
		res[0] = 1;
		for(int i = 1; i < arr.length; i++){
			int count = 1;
			for(int j = i - 1; j >= 0; j--){
				if(arr[j] < arr[i]){
					count = Math.max(count, res[j] + 1) ;
				}
			}
			res[i] = count;
		}
		int maxCount = 0;
		for(int i = 0; i < arr.length; i++){
			maxCount = Math.max(maxCount, res[i]);
		}
		return maxCount;
	}

}