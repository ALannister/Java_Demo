package com.lannister.java.exam;
import java.util.*;
/**
 * Created by hp on 2019/9/15.
 */
public class Main35 {
  public static void main(String []args) {
    String[] a = new String[]{"1", "2"};
    List list = Arrays.asList(a);
    list.clear();
    list.add(3);
    System.out.println(list.toString());
  }
}
