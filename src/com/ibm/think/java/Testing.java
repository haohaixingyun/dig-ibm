package com.ibm.think.java;

public class Testing {

	public Testing() {
		// TODO Auto-generated constructor stub
	}
	
	private void testing1(){
		Testing.testing2();
	}
	private static void testing2(){
		
	}
	
	
	Support support =new Support();
	public static void main(String args[]){
		
		Testing t = new Testing();
		t.testing1();
		Testing.testing2();
		
	}
	

}
	
class Support{
	
	
	private String sss ;
	
	Support(){
		
		System.out.println("ddd");
		sss="ddsdw";
		System.out.print(sss);
		
		
		
	}
	
	
	
}
