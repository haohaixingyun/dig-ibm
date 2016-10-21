package com.ibm;

public class Runnables  {
    
	public static int count = 0;
	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub
		for(int i=0; i<1000;i++){
			Runnable t = new MyThread();
			
			new Thread(t).start();
			
			
		}
		
		System.out.println(count);
	}

}

class MyThread implements Runnable{
	
	
	
	public void run(){
		
		Runnables.count ++;
		
	}
	
	
}
