package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Created by hp on 2019/9/1.
 */
//腾讯，排队

public class Main29{
  public static void main(String[] args)throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] strs3 = br.readLine().split("\\s+");
    int n = Integer.parseInt(strs3[0]);
    int m = Integer.parseInt(strs3[1]);
    String[] strs = br.readLine().split("\\s+");
    String[] strs2 = br.readLine().split("\\s+");
    int box1 = 0;
    int box2 = 0;
    int key1 = 0;
    int key2 = 0;
    for(int i=0;i<n;i++) {
      int tmp = Integer.parseInt(strs[i]);
      if(tmp % 2 == 0) box2++;
      else box1++;
    }
    for(int i=0;i<m;i++) {
      int tmp = Integer.parseInt(strs2[i]);
      if(tmp % 2 == 0) key2++;
      else key1++;
    }
    int count = 0;
    count += Math.min(box1,key2);
    count += Math.min(box2,key1);
    System.out.println(count);
  }
}