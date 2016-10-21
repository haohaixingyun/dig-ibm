package com.ibm;

public class Witch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        
		System.out.println(getValue(2));
	}
	
	public static int getValue(int i) {
        int result = 0;
        switch (i) {
        case 1:
            result = result + i;
        case 2:
            result = result + i * 2;
        case 3:
            result = result + i * 3;
        }
        return result;
    }
    //switch ++1
}
