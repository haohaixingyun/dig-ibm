package com.ibm.wc;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;


public class WordCount {


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//http://www.2cto.com/os/201605/505615.html
		//http://www.cnblogs.com/yanghuahui/p/3763820.html
		if(args.length != 2) {
            System.err.println("Error!");
            System.exit(1);
        }
		JobConf conf = new JobConf(WordCount.class);
		
		conf.setJobName("Word count mapreduce demo");
		conf.setMapperClass(WordCountMapper.class);
		conf.setReducerClass(WordCountReducer.class);
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(conf, new Path("hdfs://192.168.157.186:9000/d10000/f22222"));
		FileOutputFormat.setOutputPath(conf, new Path("hdfs://192.168.157.186:9000/d11100/f11211211"));
		
		//1. 我已经解决了，在Hadoop的bin目录下放winutils.exe，在环境变量中配置 HADOOP_HOME，把hadoop.dll拷贝到C:\Windows\System32下面即可
		//2. 
		/**
		 * Exception in thread "main" java.io.IOException: Job failed!
			at org.apache.hadoop.mapred.JobClient.runJob(JobClient.java:836)
			at com.ibm.wc.WordCount.main(WordCount.java:39)
		 * 
		 * 
		 */
			
		JobClient.runJob(conf);

	}

}
