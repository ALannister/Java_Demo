package com.lannister.java.demo.test;

import java.util.Arrays;

public class ArraysCopy2 {
	public static void main(String[] args) {
		int[][] metric = new int[3][5];
		for(int i=0;i<metric.length;i++) {
			for(int j=0;j<metric[0].length;j++) {
				metric[i][j] = i+j;
			}
		}
		int[][] metric2 = metric.clone();
		
		metric2[1][2] = 99;
		System.out.println("metric:");
		showArr(metric);
		System.out.println("metric2:");
		showArr(metric2);
		
		int[][] metric3 = new int[3][5];
		for(int i=0;i<metric.length;i++) {
			//metric3[i] = metric[i].clone();
			//metric3[i] = Arrays.copyOf(metric[i], metric[i].length);
			System.arraycopy(metric[i], 0, metric3[i], 0, metric[i].length);
		}
		metric3[0][3] = 888;
		System.out.println("metric:");
		showArr(metric);
		System.out.println("metric3:");
		showArr(metric3);
		
	}
	
	public static void showArr(int[][] arr) {
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[0].length;j++) {
				System.out.print(arr[i][j]);
				if(j<arr[0].length-1)
					System.out.print(" ");
			}
			System.out.println();
		}
	}
}
