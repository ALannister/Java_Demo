package com.lannister.java.exam;

/**
 * Created by hp on 2019/8/30.
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main28 {

/** 请完成下面这个函数，实现题目要求的功能 **/
  /** 当然，你也可以不按照这个模板来作答，完全按照自己的想法来 ^-^  **/
  static String getIndexAndLongest(String users, int k) {
    if(users == null) return null;
    char[] chs = users.toCharArray();
    int max = 0;
    int idx = 0;
    for(int i=0;i<chs.length;i++){
      if(chs[i] == 'b'){
        int m = girls(chs,i);
        if(m > max){
          max = m;
          idx = i;
        }
      }
    }

    int maxTeam = 0;
    for(int i=0;i<chs.length;i++){
      if(chs[i] == 'g' && chs[(i+chs.length+1)%chs.length] == 'b'){
        int m = maxTeam(chs,k,i);
        if(m > maxTeam){
          maxTeam = m;
        }
      }
    }
    return ""+idx+" "+maxTeam;

  }
  static int girls(char[] chs,int idx){
    int count = 0;
    int end = 0;
    for(int i=(idx-1+chs.length)%chs.length;i!=idx;i=(i+chs.length-1)%chs.length){
      if(chs[i] == 'g'){
        count++;
      }
      else{
        end = i;
        break;
      }
    }
    for(int i=(idx+1+chs.length)%chs.length;i!=idx && i!=end;i=(i+chs.length+1)%chs.length){
      if(chs[i] == 'g'){
        count++;
      }
      else{
        break;
      }
    }
    return count;
  }
  static int maxTeam(char[] chs,int k,int idx){
    int girls = 0;
    int count = 0;
    for(int i=(idx+1+chs.length)%chs.length;i!=idx;i=(i+chs.length+1)%chs.length){
      if(chs[i] == 'b'){
        count++;
      }
      else{
        girls++;
        if(girls > k) break;
      }
    }
    return count;
  }

  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    String res;

    String _users;
    int k;

    try {
      _users = in.nextLine();
      k = in.nextInt();

    } catch (Exception e) {
      _users = null;
      k=-1;
    }

    res = getIndexAndLongest(_users,k);
    System.out.println(res);

  }
}