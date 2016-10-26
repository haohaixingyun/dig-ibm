/*
 * Created on 2012-9-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ibm.bids.ds;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.logging.*;


/**
 * @author zhangnengbin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CompareDSJob {
    private String cmvcJob = null;
    private String dsJob = null;
    
	private final String BEGIN_HEAD = "BEGIN HEADER";
	private final String END_HEAD = "END HEADER";	
	private final String BEGIN_DSJOB = "BEGIN DSJOB";
	private final String END_DSJOB = "END DSJOB";
	private final String BEGIN_DSEXECJOB = "BEGIN DSEXECJOB";
	private final String END_DSEXECJOB = "END DSEXECJOB";

	private final String IGNORE_DATE_MODIFIED = "DateModified";
	private final String IGNORE_TIME_MODIFIED = "TimeModified";
    
    public CompareDSJob (String cmvcJob, String dsJob) {
    	this.cmvcJob = cmvcJob;
    	this.dsJob = dsJob;
    }
    
    public boolean compare() throws Exception {
        BufferedReader brCMVC = null;
        BufferedReader brDS = null;
        
        try {
        	brCMVC = new BufferedReader(new InputStreamReader(new FileInputStream(this.cmvcJob)));
        	brDS = new BufferedReader(new InputStreamReader(new FileInputStream(this.dsJob)));

        	String lineDS = brDS.readLine();
        	String lineCMVC = brCMVC.readLine();
        	
        	boolean isHeadFlag = true;        	
        	while (lineDS != null){
        		if (lineDS.compareTo(BEGIN_DSJOB) == 0) 
        			isHeadFlag = false;

        		if (!isHeadFlag 
        			&& lineDS.compareTo(lineCMVC) != 0 
        			&& (!(lineDS.indexOf(IGNORE_DATE_MODIFIED) > 0 && lineCMVC.indexOf(IGNORE_DATE_MODIFIED) > 0
        						|| lineDS.indexOf(IGNORE_TIME_MODIFIED) > 0 && lineCMVC.indexOf(IGNORE_TIME_MODIFIED) > 0)))
        				return false;

        		lineDS = brDS.readLine();
    			lineCMVC = brCMVC.readLine();
        	}
        }
        catch (Exception e) {
        	Logger.global.fine(e.getMessage());
        }
        finally {
        	if (brCMVC != null) {
        		brCMVC.close();
        		brCMVC = null;
        	}
        	
        	if (brDS != null) {
        		brDS.close();
        		brDS = null;  			
        	}        		
        }
        return true;
    }
}
