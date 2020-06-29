package com.lannister.java.demo.test;

import java.util.Arrays;

/**
 * Created by hp on 2019/9/23.
 * private final char value[];不可以改变引用，但可以改变数组里的值
 */
public class FinalTest {
  private final char value[];

  public FinalTest(char[] value){
    this.value = value;
  }

  public void change(int idx,char c){
    value[idx] = c;
  }

  @Override
  public String toString() {
    return "FinalTest{" +
        "value=" + Arrays.toString(value) +
        '}';
  }

  public static void main(String[] args) {
    char[] value = new char[]{'a','b','c'};
    FinalTest f = new FinalTest(value);
    System.out.println(f);
    f.change(0,'k');
    System.out.println(f);
  }
}
