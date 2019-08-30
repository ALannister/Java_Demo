package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main18{
  public static void main(String[] args)throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    char[] chs = br.readLine().toCharArray();
    ArrayList<String> list = new ArrayList<>();
    int yin = 0;
    for(int i=0;i<chs.length;i++) {
      yin = 0;
      for(int j=i;j<chs.length;j++) {
        if(j==i && chs[j] == ',') {
          list.add("--");
          break;
        }
        if(chs[j] == '"') {
          yin++;
          continue;
        }
        if(chs[j] == ',' && yin % 2 != 0) {
          continue;
        }
        if(chs[j] == ',' && yin % 2 == 0) {
          String tmp = "";
          for(int k=i;k<=j;k++) {
            tmp += chs[k];
          }
          if(tmp.charAt(0) == '"' && tmp.charAt(tmp.length()-1) == '"') {
            tmp = tmp.substring(1,tmp.length()-1);
            tmp.replace("\"\"", "\"" );
            list.add(tmp);
            i = j;
          }
        }
      }

    }
    System.out.println(list.size());
    for(String str : list) {
      System.out.println(str);
    }

  }
}