package com.ibm.secondtimesort;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

/**
 * 
 * @author yunxinghai
 * 在排序过程中按照第一个字段进行数据分发
 */

public class SecondaryPartition extends HashPartitioner<Text , NullWritable> {
	
	public int getPartition(Text key ,NullWritable value ,int numReduceTasks){
		
		
		return (key.toString().split(" ")[0].hashCode() & Integer.MAX_VALUE ) % numReduceTasks ;
	}

}
