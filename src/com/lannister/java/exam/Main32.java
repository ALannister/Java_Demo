package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by hp on 2019/9/1.
 */
public class Main32{
  public static void main(String[] args)throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    String[] strs = br.readLine().split("\\s+");
    int[] arr = new int[n];
    for(int i=0;i<n;i++) {
      arr[i] = Integer.parseInt(strs[i]);
      System.out.println(arr[i]);
    }
  }
}
