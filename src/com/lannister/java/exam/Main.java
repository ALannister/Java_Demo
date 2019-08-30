package com.lannister.java.exam;

import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args)throws IOException{
    	System.out.println(Integer.MAX_VALUE);
        Scanner sc = new Scanner(System.in);
        System.out.println(Math.ceil(3.4));
        while(sc.hasNextInt()){
            int N = sc.nextInt();
            int T = sc.nextInt();
            double[][] matrix = new double[N][4];
            for(int i=0;i<N;i++){
                for(int j=0;j<3;j++){
                    matrix[i][j] = sc.nextInt();
                }
            }
            for(int i=0;i<N;i++){
                for(int j=0;j<3;j++){
                    System.out.println(matrix[i][j]);
                }
                Math.floor(3.4);
            }
            System.out.println(maxSatify(N,T,matrix));
            
        }
    }
	public static int maxSatify(int N,int T,double[][] matrix){
        if(matrix.length == 0 ||matrix[0].length == 0) return 0;
        for(int i=0;i<matrix.length;i++){
            matrix[i][3] = matrix[i][1]/matrix[i][0];
        }
        int money = T;
        int satify = 0;
        while(money > 0){
            int idx = -1;
            double max = -1;
            for(int i=0;i<N;i++){
                if(matrix[i][3] > max && matrix[i][0] <=money && matrix[i][2]>0){
                    max = matrix[i][3];
                    idx = i;
                }
            }
            if(idx >=0){
                while(matrix[idx][2] >0 && money >= matrix[idx][0]){
                    satify += matrix[idx][1];
                    matrix[idx][2] -= 1;
                    money -= matrix[idx][0];
                }
            }else{
                return satify;
            }
            
        }
        return satify;
        
    }

}