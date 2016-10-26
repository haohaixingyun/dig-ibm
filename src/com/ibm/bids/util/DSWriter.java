/*
 * Created on 2012-9-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ibm.bids.util;

import java.io.*;

/**
 * @author zhangnengbin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DSWriter extends FileIO {
	private FileWriter file = null;
	private boolean isAppend = false;
	
	public DSWriter(String filePath) {
		super (filePath);
	}
	
	public DSWriter(String filePath, boolean isAppend) {
		super (filePath);
		this.isAppend = isAppend;
	}
	
	public void copyFrom(String fileName) throws Exception {
		BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            inBuff = new BufferedInputStream(new FileInputStream(fileName));
            outBuff = new BufferedOutputStream(new FileOutputStream(getFilePath()));
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            outBuff.flush();
        }
        catch (Exception e) {
        	throw e;
        }
        finally {
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }		
	}
	
	public void writeln(String value) throws Exception {
		if (file == null) {
			file = new FileWriter(getFilePath(), isAppend);
		}
		file.write(value + "\r\n");
	}
	
	public void close() throws Exception {
		if (file != null) {
			file.flush();
			file.close();
			file = null;
		}
	}
}
