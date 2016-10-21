package com.ibm.hadoopjoin;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapreduce.Mapper;

public class FileMap extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	private static final String student_name = "student_name.txt";
	private static final String student_grade = "student_grade.txt";
	private static final String student_l = "l";
	private static final String student_r = "r";	
	
	public void map(LongWritable key ,Text value ,Context context){
		
		//读取文件的名称 
		
		String filename =((FileSplit) context.getInputSplit()).getPath().toString();
		
		if(filename.contains(student_name)){
			
		}
		if(filename.contains(student_grade)){
			
		}
		
		
		
		
	}

}
