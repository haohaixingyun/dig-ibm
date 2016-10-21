package com.ibm.secondtimesort;

import java.io.IOException;

import javax.xml.soap.Text;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 * 
 * @author yunxinghai
 *
 *
 */

public class SecondaryMain {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
		
		
		Configuration conf = new Configuration();
		@SuppressWarnings("deprecation")
		Job job = new Job(conf,"secondary-sort");
		job.setJarByClass(SecondaryMain.class);
		job.setMapperClass(SecondaryMapper.class);
		job.setReducerClass(SecondaryReduce.class);
		job.setPartitionerClass(SecondaryPartition.class);
		job.setSortComparatorClass(SortComparator.class);
		job.setGroupingComparatorClass(SecondaryGroup.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(NullWritable.class);
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.setInputPaths(job, new Path(" "));
		FileOutputFormat.setOutputPath(job, new Path(" "));
		
		job.setNumReduceTasks(1);
		
		System.out.println(job.waitForCompletion(true) ? 0:1);

	}

}
