package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by hp on 2019/9/1.
 * 腾讯，期末总结
 */
public class Main31{
  public static void main(String[] args)throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    String[] strs = br.readLine().split("\\s+");
    int[] arr = new int[n];
    for(int i=0;i<n;i++) {
      arr[i] = Integer.parseInt(strs[i]);
    }
    long max = 0;
    for(int i=0;i<arr.length;i++){
      if(i > 0 && arr[i] == arr[i-1] ) continue;
      long tmp = score(arr,i);
      if(tmp>max) max =tmp;
    }
    System.out.println(max);
  }
  public static long score(int[] nums,int idx){
    long sum = 0;
    for(int i=idx;i>=0;i--){
      if(nums[i]>=nums[idx]){
        sum += nums[i];
      }
      else{
        break;
      }
    }
    for(int i=idx+1;i<nums.length;i++){
      if(nums[i]>=nums[idx]){
        sum += nums[i];
      }
      else{
        break;
      }
    }
    return sum*nums[idx];
  }
}
