package com.lannister.java.exam;

/**
 * Created by hp on 2019/9/4.
 */
import java.util.Scanner;

public class Main34 {
  private static int m;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    m = sc.nextInt();
    int n = sc.nextInt();
    int[] nums = new int[n];
    int max = 0;
    int sum = 0;
    for (int i = 0; i < n; i++) {
      nums[i] = sc.nextInt();
      sum += nums[i];
      max = Math.max(max, nums[i]);
    }

    System.out.println(binarySearch(nums, max, sum));

  }

  private static int binarySearch(int[] nums, int lo, int hi){
    while (lo < hi) {
      int mid = lo + (hi - lo) / 2;
      if(enough(nums, mid, m)){
        hi = mid;
      }else
        lo = mid + 1;
    }
    return lo;
  }

  private static boolean enough(int[] nums, int v, int size) {//v 容量  size：桶数量
    int idx = 0;
    while (size > 0 && idx < nums.length){
      int rest = v;
      while (idx < nums.length && rest >= nums[idx]){
        rest -= nums[idx];
        idx++;
      }
      size--;
    }
    return idx == nums.length;
  }
}
