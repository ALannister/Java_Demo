package com.lannister.java.demo.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by hp on 2019/9/8.
 */
public class CollectionsSort {
  public static void main(String[] args) {
    ArrayList<Integer> arrayList = new ArrayList<Integer>();
    arrayList.add(-1);
    arrayList.add(3);
    arrayList.add(3);
    arrayList.add(-5);
    arrayList.add(7);
    arrayList.add(4);
    arrayList.add(-9);
    arrayList.add(-7);


    System.out.println("ԭʼ����:");
    System.out.println(arrayList);

    // void reverse(List list)����ת
    Collections.reverse(arrayList);
    System.out.println("Collections.reverse(arrayList):");
    System.out.println(arrayList);

    // void sort(List list),����Ȼ�������������
    Collections.sort(arrayList);
    System.out.println("Collections.sort(arrayList):");
    System.out.println(arrayList);

    // ����������÷�
    Collections.sort(arrayList, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o2.compareTo(o1);
      }
    });
    System.out.println("���������");
    System.out.println(arrayList);
  }
}
