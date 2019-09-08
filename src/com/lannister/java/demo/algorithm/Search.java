package com.lannister.java.demo.algorithm;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by hp on 2019/9/4.
 */
public class Search {
  @Test
  public void binarySearchTest(){
    int[] arr = new int[10];
    Random r = new Random();
    for(int i=0;i<arr.length;i++){
      arr[i] = r.nextInt(50);
    }
    Arrays.sort(arr);
    Sort.printArr(arr);
    int key = r.nextInt(50);
    System.out.println("key:"+key);
    System.out.println(BinarySearch.search(arr,key));
  }

}

class BinarySearch{
  public static int search(int[] arr,int key){
    int low = 0;
    int high = arr.length - 1;
    int mid = 0;
    if(arr[low] > key || arr[high] < key || low > high){
      return -1;
    }
    while(low <= high){
      mid = low + (high - low) / 2;
      if(arr[mid] < key){
        low = mid + 1;
      }
      else if(arr[mid] > key){
        high = mid - 1;
      }
      else{
        return mid;
      }

    }
    return -1;
  }
}