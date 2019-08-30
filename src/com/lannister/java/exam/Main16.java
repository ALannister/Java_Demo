package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Single;

public class Main16 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] strs1 = br.readLine().split("\\s+");
		int[] com = new int[n];
		for (int i = 0; i < n; i++) {
			com[i] = Integer.parseInt(strs1[i]);
		}
		String[] strs2 = br.readLine().split("\\s+");
		int[] gym = new int[n];
		for (int i = 0; i < n; i++) {
			gym[i] = Integer.parseInt(strs2[i]);
		}
		int busy = 0;
		int state = 0;// 1 work 2 gym
		for (int i = 0; i < n; i++) {
			if (com[i] == 0 && gym[i] == 0) {
				state = 0;
			} else if (com[i] != 0 && gym[i] == 0) {
				if (state == 0 || state == 2) {
					busy++;
					state = 1;
				} else {
					state = 0;
				}
			} else if (com[i] == 0 && gym[i] != 0) {
				if (state == 0 || state == 1) {
					busy++;
					state = 2;
				} else {
					state = 0;
				}
			} else {
				if (state == 1) {
					busy++;
					state = 2;
				} else if (state == 2) {
					busy++;
					state = 1;
				} else {
					busy++;
					state = 2;
				}
			}
		}
		System.out.println(n - busy);
	}
}