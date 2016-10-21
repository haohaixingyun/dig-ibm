package com.ibm.secondtimesort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SecondaryGroup extends WritableComparator {
	
	@SuppressWarnings("rawtypes")
	public int compare(WritableComparable key1,WritableComparable key2){
		
		if(Integer.parseInt(key1.toString().split(" ")[0] )== Integer.parseInt(key1.toString().split(" ")[0])){
			
			return 0 ;
			
			
		}else if (Integer.parseInt(key1.toString().split(" ")[0]) > Integer.parseInt(key1.toString().split(" ")[0])){
			
			return 1;
			
		}else if(Integer.parseInt(key1.toString().split(" ")[0]) < Integer.parseInt(key1.toString().split(" ")[0])){
			return -1;
		}
		
		return 0;
		
	}

}
