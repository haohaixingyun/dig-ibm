package com.ibm.dg;

public class SingletonClass {
	
	private SingletonClass(){} //定义私有构造方法
	private static SingletonClass install=null;
	public static SingletonClass getInstall(){
		if(install == null){
			synchronized(SingletonClass.class){ //synchronized 当两个并发线程访问同一个对象object中的这个synchronized(this)同步代码块时，
				                               //一个时间内只能有一个线程得到执行。另一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块。
				if(install == null){
					install = new SingletonClass();
				}
			}
		}
		return install;
	}
}
