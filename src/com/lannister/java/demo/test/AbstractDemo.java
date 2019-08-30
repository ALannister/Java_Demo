package com.lannister.java.demo.test;

public abstract class AbstractDemo {
	int a;
	public  AbstractDemo(int a) {
		this.a = a;
	}

	//public abstract static void f1(); // 报错public abstract native void f2(); //报错

	//public abstract synchronized void f3(); // 报错

	//private abstract void f4(); // 报错

	abstract void f5(); // 编译通过
	
	public abstract void f6(); // 编译通过
	
	protected abstract void f7(); // 编译通过
	
	//void f8();//报错
}
