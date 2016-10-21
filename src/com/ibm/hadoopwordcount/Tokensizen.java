package com.ibm.hadoopwordcount;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class Tokensizen extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	private static final IntWritable one = new IntWritable(1);
	
	private Text word = new Text();
	
	
	
	public void map(LongWritable kye ,Text value ,Context context) throws IOException, InterruptedException{
		
		StringTokenizer itr = new StringTokenizer(value.toString());
		
		String pth = ((FileSplit)context.getInputSplit()).getPath().toString();
		
		while(itr.hasMoreTokens()){
			
			//word = itr.nextToken(); wrong
			word.set(itr.nextToken());
			context.write(word, one);
			
			
		}
		
		
	}

}
