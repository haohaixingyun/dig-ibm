/*
 * Created on 2012-9-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ibm.bids.ds;

import com.ibm.bids.util.*;
import com.ibm.bids.logs.*;

import java.io.*;
import java.util.logging.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhangnengbin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class DSCompare {

    private final String CONFIG_FILE = "config.ini";
    private final String CMVC_FOLDER = "cmvc_folder";
    private final String DS_FOLDER = "ds_folder";
    private final String RESULT_FOLDER = "result_folder";
    private final String WORK_FOLDER = "work_folder";
    private final String LOG_FOLDER = "log_folder";
    /**
     * 
    private String cmvcFolder = null;
    private String dsFolder = null;
    private String resultFolder = null;
    private String workFolder = null;    
    private String logFolder = null;
    
     */
    
    private String cmvcFolder = "C:\\workplacebus\\DSCompare\\compare\\cmvc\\";
    private String dsFolder = "C:\\workplacebus\\DSCompare\\compare\\ds\\";
    private String resultFolder = "C:\\workplacebus\\DSCompare\\compare\\split";
    private String workFolder = "C:\\workplacebus\\DSCompare\\logs";    
    private String logFolder = "C:\\workplacebus\\DSCompare\\logs";
    
    private boolean isSplitFlag = false;
    private boolean isCompareFlag = true;
    
    private String tempLogFile = "temp.log";
    private String tempResultFile = "temp.rlt";
	private FileHandler logFileHandler = null;
	private FileHandler resultFileHandler = null;

	public static void main(String[] args) {
		//System.out.println("dfdfdfde");
		new DSCompare(args).start(); 		
	}
	
    public DSCompare(String[] args) {    	
        for(int i = 0; i < args.length; i++) {
            if(args[i].equals("-c")){
            	isCompareFlag = true;                
            }
            else if(args[i].equals("-s")){
            	isSplitFlag = true;
            }
        }
    }

	public void start() {
		try {
			initLogHander();
			System.out.println("fdfd");
			//readInit();		
			System.out.println("clearWorkFolder start ");
			clearWorkFolder();		
			System.out.println("clearWorkFolder end");
			/**
			 * if (this.isSplitFlag) {
			 
				
				Logger.global.info("Start to split the big DS files under the folder " + this.DS_FOLDER);
				splitDSFile();
			}
			*/
			if (isCompareFlag) {
				System.out.println("Split start");
				
				splitDSFile();
				System.out.println("Split done");
				System.out.println("compare start");
				compareDSJobs();
				System.out.println("compare done");
			}
			
			Logger.global.info("Completed successfully!");
		}
		catch (Exception e) {
			Logger.global.severe("Completed failed!");
			Logger.global.severe(e.getMessage());
		}
		finally {			
			generateLog();
		}
		
	}

	private void readInit() throws Exception {
        IniReader reader = new IniReader(CONFIG_FILE);    
        
        cmvcFolder = reader.getValue(CMVC_FOLDER);
        System.out.println(cmvcFolder);
        dsFolder = reader.getValue(DS_FOLDER);
        resultFolder = reader.getValue(RESULT_FOLDER);
        workFolder = reader.getValue(WORK_FOLDER);
        logFolder = reader.getValue(LOG_FOLDER);
	}
	
	private void clearWorkFolder() {
        File[] file = (new File(this.workFolder)).listFiles();
        
        for (int i = 0; i < file.length; i++) {
        	if (file[i].isFile()) {
        		file[i].delete();
        	
        	}
        }
	}
	private void splitDSFile() throws Exception {
        File[] file = (new File(this.dsFolder)).listFiles();
        System.out.println(this.dsFolder);
        for (int i = 0; i < file.length; i++) {
        	if (file[i].isFile() && file[i].getName().indexOf(".dsx") > -1) {
        		String filePath = file[i].getAbsolutePath();	        		
        		SplitDSFile Split = new SplitDSFile(filePath, this.workFolder);
        		
        		Split.splitFile();
        		
        		
        		
        	}
        }		
	}
	
	private void compareDSJobs() throws Exception {
        Map map = new HashMap();
        File[] cmvsFiles = (new File(this.cmvcFolder)).listFiles();
        for (int i = 0; i < cmvsFiles.length; i++) {
        	if (cmvsFiles[i].isFile() && cmvsFiles[i].getName().indexOf(".dsx") > -1) {
        		map.put(cmvsFiles[i].getName(), cmvsFiles[i].getAbsolutePath());
        	}
        }
        
        ArrayList notExistJobs = new ArrayList(); 
        ArrayList diffJobs = new ArrayList();
		
        File[] dsFiles = (new File(this.workFolder)).listFiles();
        for (int i = 0; i < dsFiles.length; i++) {
        	if (dsFiles[i].isFile() && dsFiles[i].getName().indexOf(".dsx") > -1) {
        		if (map.get(dsFiles[i].getName()) != null) {
        			Logger.global.info("Comparing the job " + dsFiles[i].getName());
        			CompareDSJob compare = new CompareDSJob(map.get(dsFiles[i].getName()).toString(), dsFiles[i].getAbsolutePath());
        			if (!compare.compare()) 
        				diffJobs.add(dsFiles[i].getName());
        		}
        		else {
        			notExistJobs.add(dsFiles[i].getName());
        		}
        	}
        } 
        
        outputCompareResult(notExistJobs, diffJobs);        
	}
	
	private void outputCompareResult(ArrayList notExistJobs, ArrayList diffJobs) throws Exception {	
		Logger logger = Logger.getLogger("DS Result");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
		FileHandler fileHandler = new FileHandler(this.tempResultFile);	
        fileHandler.setLevel(Level.INFO);
        fileHandler.setFormatter(new ResultFormatter());
        logger.addHandler(fileHandler);
        
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (notExistJobs.size() > 0 || diffJobs.size() > 0) {
        	
        	if (notExistJobs.size() > 0) { 
        		logger.info(dt.format(new Date()) + ": " + new Integer(notExistJobs.size()).toString() + " jobs don't exist in CMVC.");
        		logger.info("");
        		for (int i = 0; i < notExistJobs.size(); i++) {
        			logger.info(notExistJobs.get(i).toString());
        		}
        	}
        	logger.info("");
        	
        	if (diffJobs.size() > 0) {
        		logger.info(dt.format(new Date()) + ": " + new Integer(diffJobs.size()).toString() + " jobs in CMVC are different from the ones in DS project.");
        		logger.info("");
        		for (int i = 0; i < diffJobs.size(); i++) {
        			logger.info(diffJobs.get(i).toString());
        		}
        	}
        }
        else 
        	logger.info(dt.format(new Date()) + " No any difference");

		DSWriter write = new DSWriter(new File(this.resultFolder).getAbsolutePath() + "\\" + df.format(new Date()) + ".rlt");
		write.copyFrom(this.tempResultFile);             
	}
	
	
	private void initLogHander() throws Exception {
		Logger.global.setLevel(Level.ALL);
	       
        logFileHandler = new FileHandler(this.tempLogFile);        
        logFileHandler.setLevel(Level.INFO);
        logFileHandler.setFormatter(new SimpleLoggerFormatter());
        Logger.global.addHandler(logFileHandler); 
	}
	
	private void generateLog() {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
			String logFilePath = new File(this.logFolder).getAbsolutePath() + "\\" + df.format(new Date()) + ".log";
			DSWriter write = new DSWriter(logFilePath);
			write.copyFrom(this.tempLogFile);
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
