package com.ibm.secondtimesort;


import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * 
 * @author yunxinghai
 * I have no idea what's the result of the map out put
 * what's the value of the key ? 
 * what's the values of iterator ? 
 */

public class SecondaryReduce extends Reducer<Text, IntWritable, NullWritable, Text> {
	
	protected void reduce(Text key ,Iterable<Text> values ,Context context) throws IOException, InterruptedException{
		
		// Iterable or iterator
		
		System.out.println(key.toString()); //what's the value of key ???
		
		for(Text result : values ){
			
			context.write(NullWritable.get(),result);
			
		}
		
	}
	 

}
