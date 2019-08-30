package com.lannister.java.demo.algorithm;

import java.util.Random;

import org.junit.Test;

public class Sort {
	public static void printArr(int[] arr) {
		System.out.print("[");
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			if(i < arr.length - 1) {
				System.out.print(" ");
			}
		}
		System.out.print("]\n");
	}
	@Test
	public void quickSortTest() {
		Random random = new Random();
		int[] arr = new int[10];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt(100);
		}
		System.out.println("bofore:");
		printArr(arr);
		QuickSort.sort(arr, 0, arr.length - 1);
		System.out.println("after:");
		printArr(arr);
	}
	
	@Test
	public void heapSortTest() {
		Random random = new Random();
		int[] arr = new int[10];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt(100);
		}
		System.out.println("bofore:");
		printArr(arr);
		HeapSort.sort(arr);
		System.out.println("after:");
		printArr(arr);
	}
}

class QuickSort{
	public static int[] sort(int[] arr, int low, int high) {
		int l = low;
		int h = high;
		int key = arr[l];
		
		while(l < h) {
			while(l < h && arr[h] >= key) {
				h--;
			}
			if(l < h) {
				swap(arr, l, h);
				l++;
			}
			while(l < h && arr[l] <= key) {
				l++;
			}
			if(l < h) {
				swap(arr, l, h);
				h--;
			}
		}
		if(l - 1 > low) sort(arr, low, l -1);
		if(h + 1 < high) sort(arr, h+1, high);
		return arr;
	}
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}

class HeapSort{
	public static void sort(int[] arr) {
		for(int i = (arr.length - 1 - 1) / 2; i >= 0; i--) {
			adjustHeap(arr, i, arr.length);
		}
		
		for(int i = arr.length - 1; i > 0; i--) {
			swap(arr, i, 0);
			adjustHeap(arr, 0, i);
		}
	}
	
	public static void adjustHeap(int[] arr, int i, int length) {
		int tmp = arr[i];
		for(int k = 2 * i + 1; k < length; k = 2 * k + 1) {
			if(k + 1 < length && arr[k] < arr[k + 1]) {
				k++;
			}
			if(arr[k] > tmp) {
				swap(arr, i, k);
				i = k;
			}else {
				break;
			}
		}
	}
	
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}