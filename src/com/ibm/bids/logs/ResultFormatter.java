/*
 * Created on 2012-9-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ibm.bids.logs;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * @author zhangnengbin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ResultFormatter extends Formatter {
	
	public String format(LogRecord record) { 
        return record.getMessage()+"\n";
	}
}
