/*
 * Created on 2012-9-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ibm.bids.ds;

import com.ibm.bids.util.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.logging.*;

import com.ibm.bids.util.FileIO;

/**
 * @author zhangnengbin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SplitDSFile extends FileIO {
	private String workFolder = null;
	private String headFile = null;
	private String tempFile = null;
	
	
	private final int STATUS_INIT = 0;
	private final int STATUS_READ_HEAD = 1;
	private final int STATUS_READ_DSJOB = 2;
	private final int STATUS_READ_DSEXECJOB = 3;
	
	private final String BEGIN_HEAD = "BEGIN HEADER";
	private final String END_HEAD = "END HEADER";	
	private final String BEGIN_DSJOB = "BEGIN DSJOB";
	private final String BEGIN_DSRECORD = "BEGIN DSRECORD";
	private final String BEGIN_DSQSRULEASSEMBLY="BEGIN DSQSRULEASSEMBLY";
	private final String END_DSQSRULEASSEMBLY="END DSQSRULEASSEMBLY";
	private final String BEGIN_DSPARAMETERSETS="BEGIN DSPARAMETERSETS";
	private final String END_DSPARAMETERSETS="END DSPARAMETERSETS";
	private final String END_DSJOB = "END DSJOB";
	private final String END_DSRECORD = "END DSRECORD";
	private final String BEGIN_DSEXECJOB = "BEGIN DSEXECJOB";
	private final String END_DSEXECJOB = "END DSEXECJOB";
	private final String BEGIN_DSQSRUNTIME = "BEGIN DSQSRUNTIME";
	private final String END_DSQSRUNTIME = "END DSQSRUNTIME";
	
	
	private int status = -1;
	
	public SplitDSFile(String filePath, String workFolder) {
        super(filePath);
        this.workFolder = new File(workFolder).getAbsolutePath();
        
        this.headFile = this.workFolder + "\\head.tmp";
        this.tempFile = this.workFolder + "\\temp";
    }
    
    public void splitFile() throws Exception {
    	
        if (!isExist()) {
        	throw new Exception("The file " + getFilePath() + " does not exist.");
        }
        
        String filePath = getFilePath();
        Logger.global.info("Splitting DS file " + filePath + " ...");

        status = STATUS_INIT;
        
        BufferedReader br = null;
        DSWriter file = null;
        try {
        	br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        	String line = br.readLine();
        	System.out.println(line);
        	String jobName = null;
        	while (line != null){
        		if (line.compareTo(BEGIN_HEAD) == 0) {
                	file = new DSWriter(this.headFile);
                	file.writeln(line);
                	System.out.println(line);
                }
                //else if (line.compareTo(END_HEAD) == 0){
        		else if (line.compareTo(BEGIN_DSQSRULEASSEMBLY) == 0){
                	file.writeln(line);
                	System.out.println(line);
                	file.close();
                	file = null;
                }
                else if (line.trim().compareTo(BEGIN_DSRECORD) == 0 ) {               	
                	file = new DSWriter(this.tempFile, true);
                	file.copyFrom(this.headFile);
                	file.writeln(line);
                	System.out.println(line);
                	// Get the job name
                	line = br.readLine();
                	if(line.trim() == null|| line.trim().equals("")){
                		file.writeln(line);
                		line = br.readLine();
                	}
                	System.out.println(line);
                	jobName = this.getJobName(line);
                	file.writeln(line);  
                	System.out.print("testing");
                }
                else if (line.trim().compareTo(END_DSRECORD) == 0) {
                	file.writeln(line);
                	file.close();               	
                	
                	DSWriter ds = new DSWriter(this.workFolder + "\\" + jobName + ".dsx");
                	ds.copyFrom(file.getFilePath());
                	
                	ds.close();
                	ds = null;
                	file = null;
                }
                else if (line.trim().compareTo(BEGIN_DSQSRUNTIME) == 0) {                	
                	// Get the job name
                	String x = line;
                	System.out.println("BEGIN_DSQSRUNTIMEe");
                	line = br.readLine();
                	if(line.trim() == null || line.trim().equals("")){
                		file.writeln(line);
                		line = br.readLine();
                		System.out.println("if is done");
                	}
                	System.out.println("Line for job name"+line);
                	jobName = this.getJobName(line);
                	
                	file = new DSWriter(this.workFolder + "\\" + jobName + ".dsx", true);
                	//file.writeln(BEGIN_DSEXECJOB);   
                	file.writeln(x); 
                	file.writeln(line);
                }
                else if (line.trim().compareTo(END_DSQSRUNTIME) == 0) {
                	file.writeln(line);
                
                	file.writeln("END DSQSRULEASSEMBLY");
                	
                	file.close();
                	file = null;
                }
                else {
                	if(file != null){
                		System.out.println("here");
                		file.writeln(line);
                		
                	}else{
                		System.out.println("DEBUG-- FILE IS NULL --> "+ line);
                	}
                	
                }
                line = br.readLine();
                System.out.println(line);
                
        	}
        }
        catch (Exception e) {
        	e.printStackTrace();
        	System.out.println(e);
        	throw e;
        	
        }
        finally {
            br.close();    
           /**
            *  if (file != null) {
           
            	file.close();
            	file = null;
            	
            }
             */
            
            clearTempFile();
        }        
    	
    }
    private String getJobName(String line) throws Exception {
    	
    	System.out.println("Print ::" + line);
    	
    	int pos = line.indexOf("\"") + 1;
    	//int pos1 = line.indexOf("\"", pos);
    	int pos1 = line.indexOf(".", pos);
     	String jobName = line.substring(pos, pos1);
     	System.out.println("job name ="+jobName);
    	if (line.indexOf("Identifier") != -1 && jobName != null)  
    		return jobName.replaceAll("::", "_");
    	else    	
    		throw new Exception("Job name can not be read: " + line);
    	
    }
    private void clearTempFile() {
        File[] file = (new File(this.workFolder)).listFiles();
        for (int i = 0; i < file.length; i++) {
        	if (file[i].isFile() && file[i].getName().indexOf(".dsx") == -1) {
        		file[i].delete();	        		
        	}
        }

    }
}
