package com.ibm.w3;

import java.io.FileInputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class Dtf {
    final public static String HDFS_PATH ="hdfs://dias:9000";
    public static final String DIR_PATH ="/d11111";
    public static final String FILE_PATH ="/d10000/f22222";
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		
		final FileSystem fileSystem = FileSystem.get(new URI(HDFS_PATH), new Configuration());
        
		//create folder
		
		//fileSystem.mkdirs(new Path(DIR_PATH));
		
		// upload
		final FSDataOutputStream out=fileSystem.create(new Path(FILE_PATH));
		FileInputStream in = new FileInputStream("C:/dev/dddd.txt");
		IOUtils.copyBytes(in, out, 1024,true);
		//download
		//final FSDataInputStream in = fileSystem.open(new Path(FILE_PATH));
		//IOUtils.copyBytes(in, System.out, 1024, true);
		
		//remove files
		
		//fileSystem.delete(new Path(FILE_PATH), true);
	}

}
