package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//�ϲ�������
public class Main24{
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	    String[] strs1 = br.readLine().split("\\s+");
	    String[] strs2 = br.readLine().split("\\s+");
	    int idx2 = 0;
		for(int i=0;i<strs1.length;i++) {
			System.out.print(strs1[i] + " ");
			if((i+1)%4 == 0 && idx2 < strs2.length) {
				System.out.print(strs2[idx2++] + " ");
			} 
		}
		for(int i=idx2;i<strs2.length;i++) {
			System.out.print(strs2[i] + " ");
		}
	}
}