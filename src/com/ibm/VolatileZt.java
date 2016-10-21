package com.ibm;

public class VolatileZt {
	
	public static int count = 0;
	
	public static void inc(){
		
		
		
		count ++;
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i = 0;i<1000; i++){
			new Thread(new Runnable(){
				
				public void run(){
					
					inc();
				}
				
			}).start();
			
			
		}
		
		System.out.println(count);
	}

	
}
