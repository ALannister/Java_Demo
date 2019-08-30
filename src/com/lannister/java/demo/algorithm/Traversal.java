package com.lannister.java.demo.algorithm;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

public class Traversal {
	
	public static void visit(TreeNode node) {
		System.out.print(node.val + " ");
	}
	
	//�ݹ�ʵ�ֶ������������
	public static void preOrder(TreeNode node){
		if(node != null) {
			visit(node);
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	//�ݹ�ʵ�ֶ������������
	public static void inOrder(TreeNode node){
		if(node != null) {
			inOrder(node.left);
			visit(node);
			inOrder(node.right);
		}
	}
	//�ݹ�ʵ�ֶ������������
	public static void lastOrder(TreeNode node){
		if(node != null) {
			lastOrder(node.left);
			lastOrder(node.right);
			visit(node);
		}
	}
	
	//ͨ������ʵ�ֶ������Ĺ�����ȱ���
	public static void layerTraversal(TreeNode node) {
		Queue<TreeNode> queue = new LinkedList<>();
		if(node != null) queue.offer(node);
		while(!queue.isEmpty()) {
			TreeNode curr = queue.poll();
			visit(curr);
			if(curr.left != null) queue.offer(curr.left);
			if(curr.right != null) queue.offer(curr.right);
		}
	}
	
	@Test
	public void traversalTest() {
		
		TreeNode n0 = new TreeNode(0);
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		TreeNode n7 = new TreeNode(7);
		TreeNode n8 = new TreeNode(8);
		
		n0.left = n1;
		n0.right = n2;
		n1.left = n3;
		n1.right = n4;
		n2.left = n5;
		n2.right = n6;
		n3.left = n7;
		n3.right = n8;
		
		System.out.println("���������������");
		preOrder(n0);
		
		System.out.println("\n���������������");
		inOrder(n0);
		
		System.out.println("\n���������������");
		lastOrder(n0);
		
		System.out.println("\n���������������");
		layerTraversal(n0);
	}
}

class TreeNode{
	int val;
	TreeNode left = null;
	TreeNode right = null;
	public TreeNode(int val) {
		super();
		this.val = val;
	}
}