package com.lannister.java.exam;
import java.util.*;
import java.io.*;
/**
 * Created by hp on 2019/9/23.
 * public Integer calculate(String str){}
 * param: str, �Ϸ��ļ�����ʽ��ֻ���������ͼӼ��ţ�
 * ���� "-1+20-3" return: ������ʽ�Ľ��
 */
public class Main36 {
  public static void main(String[] args)throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str;
    while((str = br.readLine()) != null){
      System.out.println(calculate(str));
    }

  }
  public static Integer calculate(String str){
    String[] strs = str.replace("-","+-").split("\\+");
    Integer sum = new Integer(0);
    for(int i=0;i<strs.length;i++){
      if(strs[i] == null || strs[i].equals("")) continue;
      sum += Integer.parseInt(strs[i]);
    }
    return sum;
  }
}
