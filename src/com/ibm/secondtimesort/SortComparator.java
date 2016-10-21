package com.ibm.secondtimesort;



import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * 
 * @author yunxinghai
 * 只要是程序中用到排序的地方 都会调用这个方法 （map 的输入只有key 没有value ）  
 */

public class SortComparator extends WritableComparator {
	
	//为什么要把构造方法写这里 ？
	protected SortComparator(){
		super(Text.class,true);
	}
	
	@SuppressWarnings("rawtypes")
	public int compare(WritableComparable key1 ,WritableComparable key2){
		
		//如果第一个字段相同就根据第二个字段排序
		if(Integer.parseInt(key1.toString().split(" ")[0]) == Integer.parseInt(key2.toString().split(" ")[0])){
			if(Integer.parseInt(key1.toString().split(" ")[1]) > Integer.parseInt(key2.toString().split(" ")[1])){
				return 1;
			}else if(Integer.parseInt(key1.toString().split(" ")[1]) < Integer.parseInt(key2.toString().split(" ")[1])){
				return -1;
			}else if(Integer.parseInt(key1.toString().split(" ")[1]) < Integer.parseInt(key2.toString().split(" ")[1])){
				return 0;
			}
			//如果第一个字段不同 就按照第一个字段排序 
		}else{
			if(Integer.parseInt(key1.toString().split(" ")[0]) > Integer.parseInt(key2.toString().split(" ")[0])){
				return  1;
			}else if (Integer.parseInt(key1.toString().split(" ")[0]) < Integer.parseInt(key2.toString().split(" ")[0])){
				return -1;
			}
			
			
		}
		
		return 0;
	}

}
