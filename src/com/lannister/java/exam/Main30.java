package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by hp on 2019/9/1.
 * 腾讯，排队
 */

public class Main30{
  public static void main(String[] args)throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    ArrayList<Num> list = new ArrayList<>();

    for(int i=0;i<n;i++) {
      String[] strs = br.readLine().split("\\s+");
      Num num = new Num(Integer.parseInt(strs[0]),Integer.parseInt(strs[1]));
      list.add(num);
    }
    Collections.sort(list, new Comparator<Num>() {
      @Override
      public int compare(Num o1, Num o2) {
        int num1 = o2.a - o1.a;
        int num2 = o2.b - o1.b;
        return num2 - num1;
      }
    });
    long sum = 0;
    for(int i=0;i<list.size();i++){
      Num num = list.get(i);
      sum += (num.a-num.b)*i+num.b*(n-1);
    }
    System.out.println(sum);
  }
  static class Num{
    int a = 0;
    int b = 0;
    public Num(int a,int b){
      this.a = a;
      this.b = b;
    }
  }
}