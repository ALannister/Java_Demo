package com.lannister.java.demo.collection;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

public class CollectionAddNull {
	@Test
	public void addNull() {
		HashSet<String> set = new HashSet<>();
		set.add(null);
		set.add(null);
		System.out.println("HashSet: " + set);
		
		TreeSet<String> set1 = new TreeSet<>();
		try {
			set1.add(null);
		} catch (Exception e) {
			System.out.println("TreeSet can't add null!");
		}
		System.out.println("TreeSet: " + set1);
		
		ArrayList<String> list = new ArrayList<>();
		list.add(null);
		list.add(null);
		System.out.println("ArrayList: " + list);
		
		LinkedList<String> list2 = new LinkedList<>();
		list2.add(null);
		list2.add(null);
		System.out.println("LinkedList: " + list2);
		
		Vector<String> list3 = new Vector<>();
		list3.add(null);
		list3.add(null);
		System.out.println("Vector: " + list3);
		
		Stack<String> list4 = new Stack<>();
		list4.add(null);
		list4.add(null);
		System.out.println("Stack: " + list4);
		
		
		PriorityQueue<String> queue = new PriorityQueue<>();
		try {
			queue.add(null);
		} catch (Exception e) {
			System.out.println("PriorityQueue can't add null!");
		}
		System.out.println("PriorityQueue: " + queue);
		
		ArrayDeque<String> queue2 = new ArrayDeque<>();
		try {
			queue2.add(null);
		} catch (Exception e) {
			System.out.println("ArrayDeque can't add null!");
		}
		System.out.println("ArrayDeque: " + queue2);
		
		HashMap<String, String> map = new HashMap<>();
		map.put(null, null);
		map.put(null, null);
		System.out.println("HashMap: " + map);
		
		Hashtable<String, String> map2 = new Hashtable<>();
		try {
			map2.put(null, "value");
		} catch (Exception e) {
			System.out.println("Hashtable Key can't be null");
		}
		try {
			map2.put("key", null);
		} catch (Exception e) {
			System.out.println("Hashtable Value can't be null");
		}
		System.out.println("Hashtable: " + map2);
		
		TreeMap<String, String> map3 = new TreeMap<>();
		try {
			map3.put(null, "value");
		} catch (Exception e) {
			System.out.println("TreeMap Key can't be null");
		}
		try {
			map3.put("key", null);
		} catch (Exception e) {
			System.out.println("TreeMap Value can't be null");
		}
		System.out.println("TreeMap: " + map3);
		
		WeakHashMap<String, String> map4 = new WeakHashMap<>();
		map4.put(null, null);
		map4.put(null, null);
		System.out.println("WeakHashMap: " + map4);
		
		ConcurrentHashMap<String, String> map5 = new ConcurrentHashMap<>();
		try {
			map5.put(null, "value");
		} catch (Exception e) {
			System.out.println("ConcurrentHashMap Key can't be null");
		}
		try {
			map5.put("key", null);
		} catch (Exception e) {
			System.out.println("ConcurrentHashMap Value can't be null");
		}
		System.out.println("ConcurrentHashMap: " + map5);
	}
}
