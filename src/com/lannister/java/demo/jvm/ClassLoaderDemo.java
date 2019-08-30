package com.lannister.java.demo.jvm;

import java.io.IOException;
import java.io.InputStream;


/**
 * 类加载器在类相等判断中的影响
 * 
 * instanceof关键字
 * 
 */

public class ClassLoaderDemo {
    public static void main(String[] args) throws Exception {

        // 使用ClassLoaderTest的类加载器加载本类
        Object obj1 = ClassLoaderDemo.class.getClassLoader().loadClass("com.lannister.java.demo.jvm.ClassLoaderDemo").newInstance();
        System.out.println(obj1.getClass());
        System.out.println(obj1 instanceof com.lannister.java.demo.jvm.ClassLoaderDemo);

        // 使用自定义类加载器加载本类
        Object obj2 = new MyClassLoader().loadClass("com.lannister.java.demo.jvm.ClassLoaderDemo").newInstance();
        System.out.println(obj2.getClass());
        System.out.println(obj2 instanceof com.lannister.java.demo.jvm.ClassLoaderDemo);
    }
}

class MyClassLoader extends ClassLoader{
    
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
            InputStream is = getClass().getResourceAsStream(fileName);
            if (is == null) {
                return super.loadClass(name);
            }
            byte[] b = new byte[is.available()];
            is.read(b);
            return defineClass(name, b, 0, b.length);   
        } catch (IOException e) {
            throw new ClassNotFoundException();
        }
    }
}