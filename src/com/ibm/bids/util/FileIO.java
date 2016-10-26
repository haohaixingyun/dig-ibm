/*
 * Created on 2012-9-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ibm.bids.util;

import java.io.File;

/**
 * @author zhangnengbin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class FileIO {
    private String filePath = null;
    
    public FileIO(String filePath) {
        this.filePath = filePath;
    }
    
    public String getFilePath() {
        return filePath;
    }
    
    public boolean isExist() {
        File file = new File(filePath);
        return file.exists();
    }
    
    protected int getLength(String value) {
        int length = -1;
        try {                    
            if (value != null)
                length = Integer.parseInt(value.trim().toLowerCase());
            else
                length = -1;
        }
        catch (Exception ex) {
            length = -1;
        }
        return length;
    }

}
