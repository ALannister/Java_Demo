package com.lannister.java.demo.test;

public abstract class AbstractDemo {
	int a;
	public  AbstractDemo(int a) {
		this.a = a;
	}

	//public abstract static void f1(); // ����public abstract native void f2(); //����

	//public abstract synchronized void f3(); // ����

	//private abstract void f4(); // ����

	abstract void f5(); // ����ͨ��
	
	public abstract void f6(); // ����ͨ��
	
	protected abstract void f7(); // ����ͨ��
	
	//void f8();//����
}
