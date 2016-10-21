package com.ibm.secondtimesort;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 0  1
 * 3  4
 * 45 12
 * @throws InterruptedException 
 * @throws IOException 
 * 
 */

public class SecondaryMapper extends Mapper<LongWritable ,Text ,Text ,NullWritable> {
	
	
	
	protected void map(LongWritable key ,Text value ,Context context) throws IOException, InterruptedException{
		
		context.write(value, NullWritable.get());// 直接把读进来的values 数据 输出 
	}

}
