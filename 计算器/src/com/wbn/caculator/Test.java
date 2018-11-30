package com.wbn.caculator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Test {
	static String string = "15+1.5#";
	public Test() {
		String str[] = new String[10];
		int i =0,j=0;
		float ch;
		String s;
		do {
			s =String.valueOf(string.charAt(j));
			if(s.equals(".")){
				do {
					str[i-1] = str[i-1]+s;
					j++;
					s =String.valueOf(string.charAt(j));
				}while(string.charAt(j)>='0' && string.charAt(j)<='9');
			}
			else {
				str[i] = s;
				i++;
				j++;
			}
			
		}while(!str[i-1].equals("#"));
		for(int r =0;r<str.length;r++) {
			System.out.println(str[r]);
		}
		
	}
	public static void main(String[] args) {
//		Test test = new Test();
		
		KeyListener keyListener = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println(e.getKeyChar());
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		};
	}
}
