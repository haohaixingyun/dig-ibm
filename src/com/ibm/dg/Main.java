package com.ibm.dg;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SingletonClass s1 =SingletonClass.getInstall();
		SingletonClass s2 = SingletonClass.getInstall();
		
		System.out.println(s1==s2);   //true
		System.out.println(s1.equals(s2));  //true
	}
}

//单例模式的应用场景 与机会 ： 
//一般情况下这个在项目中都是怎么使用呢 ？
//资源池创建连接 ,数据库连接 ，但是 一般数据库都是用连接池的 ，单例模式会造成等待 

