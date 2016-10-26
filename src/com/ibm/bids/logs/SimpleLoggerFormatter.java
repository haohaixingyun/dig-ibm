/*
 * Created on 2012-9-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ibm.bids.logs;

import java.text.SimpleDateFormat;
import java.util.logging.*;
import java.util.logging.Formatter;
import java.util.*;

/**
 * @author zhangnengbin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SimpleLoggerFormatter extends Formatter {
	
	public String format(LogRecord record) { 
		Date date = new Date(record.getMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return record.getLevel() + " " + df.format(date) + ": " + record.getMessage()+"\n";
	}
}
