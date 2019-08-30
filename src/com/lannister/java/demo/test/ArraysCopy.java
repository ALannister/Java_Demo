package com.lannister.java.demo.test;
import java.util.Arrays;

public class ArraysCopy {
	public static void main(String[] args) {
		int arr[] = {1,2,3};
		int[] copy = Arrays.copyOfRange(arr, 1, 1);
		System.out.println(copy.length);
		
		int[] copy1 = Arrays.copyOf(arr, 4);
		copy1[0] = 99;
		int[] copy2 = new int[3];
		System.arraycopy(arr, 0, copy2, 0, 3);
		copy2[1] = 99;
		int[] copy3 = arr.clone();
		copy3[2] = 99;
		System.out.println("arr:");
		showArr(arr);
		System.out.println("copy1:");
		showArr(copy1);
		System.out.println("copy2");
		showArr(copy2);
		System.out.println("copy3");
		showArr(copy3);
		
	}
	public static void showArr(int[] arr) {
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]);
			if(i<arr.length-1)
				System.out.print(" ");
			else
				System.out.println();
		}
	}
}
