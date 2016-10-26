package com.ibm.wc;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class WordCountMapper extends MapReduceBase implements
		Mapper<LongWritable, Text, Text,IntWritable>  {
	//http://my.oschina.net/muou/blog/408543
	public void map(LongWritable key, Text value,
            OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException{
		
		String line = value.toString(); //整个读入的文章成为一行
		if(line!=null){
			String[] words = line.split(" ");
			for(String word:words){
				output.collect(new Text(word),new IntWritable(1));
			}
		}
		
	}

}
