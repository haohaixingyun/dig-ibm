package com.ibm.hadoopjoin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class JoinReduce extends Reducer<Text, Text, Text, Text> {
	
	private static final String student_l = "l";
	private static final String student_r = "r";	
	
	protected void reduce(Text key, Iterator<Text> values ,Context context) throws IOException, InterruptedException{
		
		//Iterator<Text> itr = values.i;
		
		String StudentName = null ;
		List<String> StudentCourse = new ArrayList<String>();
		
		while(values.hasNext()){
			
			String[] info = values.next().toString().split("\t");
			
			if(info[1].contains(student_l)){
				
				StudentName = info[0];
				
			}
			if(info[1].contains(student_r)){
				
				StudentCourse.add(info[1]);
				
			}
			
		}
		
		for(int i=0 ;i< StudentCourse.size()-1 ;i++){
			
			context.write(new Text(StudentName), new Text(StudentCourse.get(i)));
		}
		
	}

}
