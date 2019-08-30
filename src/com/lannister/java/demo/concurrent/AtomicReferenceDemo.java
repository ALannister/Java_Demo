package com.lannister.java.demo.concurrent;

import java.util.concurrent.atomic.AtomicReference;

//原子引用更新
class Player{
	String name;
	int age;
	public Player(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Player [name=" + name + ", age=" + age + "]";
	}
	
}
public class AtomicReferenceDemo {
	public static void main(String[] args) {
		Player faker = new Player("faker", 23);
		Player caps = new Player("caps", 20);
		
		AtomicReference<Player> atomicReference = new AtomicReference<Player>();
		atomicReference.set(faker);
		System.out.println(atomicReference.get());
		boolean a = atomicReference.compareAndSet(faker, caps);
		System.out.println("update success or not : " + a);
		System.out.println(atomicReference.get());
		
	}
}
