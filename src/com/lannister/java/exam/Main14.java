package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;



public class Main14{
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    Stack<Character> stack = new Stack<>();
		char[] chs = br.readLine().toCharArray();
		for(int i=0;i<chs.length;i++) {
			if(chs[i] != ']') {
				stack.push(chs[i]);
			}else {
				String content = "";
				while(!stack.isEmpty() && stack.peek() != '|') {
					content += stack.pop();
				}
				char[] chs2 = new StringBuffer(content).reverse().toString().toCharArray();
				stack.pop();
				String num = "";
				while(!stack.isEmpty() && stack.peek() != '[') {
					num += stack.pop();
				}
				stack.pop();
				int count = Integer.parseInt(new StringBuffer(num).reverse().toString());
				for(int j=0;j<count;j++) {
					for(int k=0;k<chs2.length;k++) {
						stack.push(chs2[k]);
					}
				}
			}
		}
		String res = "";
		while(!stack.isEmpty()) {
			res += stack.pop();
		}
		System.out.println(new StringBuffer(res).reverse().toString());
	}
}