package com.ibm.hadoopjoin;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapreduce.Mapper;

public class FileMap extends Mapper<LongWritable, Text, Text, Text> {
	
	private static final String student_name = "student_name.txt";
	/**
	 * 00001 ethan
	 */
	private static final String student_grade = "student_grade.txt";
	/**
	 * 00001  chinese
	 * 00001  english
	 */
	/**
	 * 输出 
	 * ethan  chinese
	 * ethan  english
	 * 
	 */
	private static final String student_l = "l";
	private static final String student_r = "r";	
	
	public void map(LongWritable key ,Text value ,Context context) throws IOException, InterruptedException{
		
		//读取文件的名称 
		
		String filename =((FileSplit) context.getInputSplit()).getPath().toString();
		
		String joinFlag = null;
		String joinKey  = null ;
		String joinvale = null ;
		
		if(filename.contains(student_name)){
			
			joinFlag = student_l ;
			joinKey=value.toString().split("\t")[0];
			joinvale=value.toString().split("\t")[1];
			
		}
		if(filename.contains(student_grade)){
			joinFlag = student_r;
			joinKey=value.toString().split("\t")[0];
			joinvale=value.toString().split("\t")[1];
		}
		
		context.write(new Text(joinKey), new Text(joinvale +"\t"+joinFlag ));
		
	}

}
