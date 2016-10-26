package com.ibm.searchcomparesplit;

import java.util.StringTokenizer;

public class TestingSplitFunction {

	//@author Xinghai Yun
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringTokenizer sub = new StringTokenizer("helo how de nwooe not adge grdge");
		System.out.println( "Token Total: " + sub.countTokens() );
		while(sub.hasMoreElements()){
			//System.out.println(sub.nextElement());
			
			System.out.println(sub.nextToken());
		}
	}

}
