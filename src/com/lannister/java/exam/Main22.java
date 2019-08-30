package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
//��������
public class Main22{
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String[] strs = br.readLine().split("\\s+");
	    int n = Integer.parseInt(strs[0]);
	    int m = Integer.parseInt(strs[1]);
		int[][] arr = new int[m][2];
		for(int i=0;i<m;i++) {
			String[] s = br.readLine().split("\\s+");
		    arr[i][0] = Integer.parseInt(s[0]);
		    arr[i][1] = Integer.parseInt(s[1]);
		}
		
		ArrayList<Integer> list = new ArrayList<>();
		while(hasFriends(arr)>0) {
			int maxId = maxStu(arr,n);
			list.add(maxId);
			for(int i=0;i<m;i++) {
				if(arr[i][0] == maxId || arr[i][1] == maxId) {
					arr[i][0] = 0;
					arr[i][1] = 0;
				}
			}
		}
		Collections.sort(list);
		System.out.println(list.size());
		for(int i=0;i<list.size();i++) {
			System.out.print(list.get(i));
			if(i<list.size()-1) {
				System.out.print(" ");
			}
		}
		System.out.println();
	}
	
	public static int hasFriends(int[][] arr) {
		for(int i=0;i<arr.length;i++) {
			if(arr[i][0] > 0) return 1;
		}
		return 0;
	}
	
	public static int maxStu(int[][] arr,int n){
		int[] stu = new int[2*n+1];
		for(int i=0;i<arr.length;i++) {
			if(arr[i][0] > 0 && arr[i][1] > 0) {
				stu[arr[i][0]] += 1;
				stu[arr[i][1]] += 1;
			}
			
		}
		int maxId = 0;
		for(int i=1;i<stu.length;i++) {
			if(stu[i] > stu[maxId]) {
				maxId = i;
			}
		}
		return maxId;
	}
}