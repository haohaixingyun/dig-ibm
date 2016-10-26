/*
 * Created on 2012-9-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ibm.bids.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;
import java.util.logging.*;

/**
 * @author zhangnengbin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class IniReader extends FileIO {
    private Map<String, String> map = null;
    public IniReader(String filePath) {
        super(filePath);
    }
    
    public String getValue(String key)  throws Exception {
        if (map == null)
            intValues();
        
        if (map != null)
            return (String)map.get(key);
        return null;
    }
    
    public void intValues() throws Exception {       
        if (!isExist())
            return;

        String filePath = getFilePath();
        Logger.global.info("Read config information from " + filePath + " ...");

        map = new HashMap<String, String>();
        BufferedReader br = null;
        try {
	    	br = new BufferedReader(
	                new InputStreamReader(new FileInputStream(filePath)));
	    	String line = "";
	    	while (line != null){
	    		line = br.readLine();
	            if(line == null) {
	            	break;
	            }
	            if (line.trim().indexOf("#") == 0)
	                continue;
	            
	            int pos = line.indexOf("=");
	            if (pos > 0) {
	                String key = line.substring(0, pos).trim().toLowerCase();
	                String value = line.substring(pos + 1).trim();
	                map.put(key, value);
	            }
	    	}
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            br.close();    
        }        
    }
}