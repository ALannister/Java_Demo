package com.lannister.java.exam;

/**
 * Created by hp on 2019/9/4.
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main33 {


  /*请完成下面这个函数，实现题目要求的功能
  当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
  ******************************开始写代码******************************/
  static String resolve(String expr) {
    char[] exp = expr.toCharArray();
    Stack<Character> stack = new Stack<>();
    LinkedList<Character> deque = new LinkedList<>();
    for(int i=0;i<exp.length;i++){
      stack.push(exp[i]);
      if(stack.peek() == ')'){
        stack.pop();
        while(stack.size()>0){
          if(stack.peek() == '('){
            stack.pop();
            while(deque.size()>0){
              stack.push(deque.pollFirst());
            }
            break;
          }
          else{
            if(stack.size() == 1) return null;
            deque.offerLast(stack.pop());
          }
        }
      }
    }
    String res = "";
    while(stack.size() > 0){
      char c = stack.pop();
      if(c=='(' || c==')') return null;
      res = c+res;
    }
    return res;
  }
  /******************************结束写代码******************************/


  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    String res;

    String _expr;
    try {
      _expr = in.nextLine();
    } catch (Exception e) {
      _expr = null;
    }

    res = resolve(_expr);
    System.out.println(res);
  }
}