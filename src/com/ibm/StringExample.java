package com.ibm;

import java.util.ArrayList;

public class StringExample {

	String str = new String("good");
	int i = 7;

    char[] ch = { 'a', 'b', 'c' };
    
    static {
    	
    	System.out.println("yunxinghai");
    	
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringExample ex = new StringExample();

	        ex.change(ex.str, ex.ch,ex.i);

	        System.out.print(ex.str + " and ");

	        System.out.print(ex.ch);
	        
	        System.out.print(ex.i);
	        
	        ArrayList list = new ArrayList(20);
	        System.out.println("hi ="+list.size());
	        
	        

	}
	public void change(String str, char ch[],int i) {

        str = "test ok";
        
        System.out.println(str);//test OK 

        ch[0] = 'g';// change content 
        
        //这一点和普通变量不一样。也就是说，对数组整体的初始化，只能在定义时实行。
        
        i = 9 ;

    }

}
