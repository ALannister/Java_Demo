package com.lannister.java.demo.collection;

import java.util.HashMap;

/**
 * Created by hp on 2019/9/8.
 */
public class HashMapTreefy {
  public static void main(String[] args) {
    HashMap<Dog,Integer> map = new HashMap<>(32);
    map.put(new Dog(),1);
    map.put(new Dog(),2);
    map.put(new Dog(),3);
    map.put(new Dog(),4);
    map.put(new Dog(),5);
    map.put(new Dog(),6);
    map.put(new Dog(),7);
    map.put(new Dog(),8);
    map.put(new Dog(),9);
  }

}
class Dog{
  @Override
  public int hashCode() {
    return 1;
  }

  @Override
  public boolean equals(Object obj) {
    return false;
  }
}