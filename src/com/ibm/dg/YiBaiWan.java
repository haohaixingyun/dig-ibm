package com.ibm.dg;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class YiBaiWan {
	
	
	public static String getRandomString(int length) { //length表示生成字符串的长度    
	       String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";       
	       Random random = new Random();       
	       StringBuffer sb = new StringBuffer();       
	       for (int i = 0; i < length; i++) {       
	           int number = random.nextInt(base.length());       
	           sb.append(base.charAt(number));       
	       }       
	       return sb.toString();       
	    }  
	
	public static final String url = "jdbc:mysql://127.0.0.1/student";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "root";  
    
    public Connection conn = null;  
    public PreparedStatement pst = null;  
	
	public static void main(String args[]){
		
		Class.forName(name);//指定连接类型 
		conn = DriverManager.getConnection(url, user, password);//获取连接  
		FileOutputStream out = null;   
		
		Random random = new Random();
		//int k = random.nextInt();
		//System.out.println(k);
		int x = 0;
		try {
			out = new FileOutputStream(new File("C:/mysql.txt"));
		
		
		while(x <5000000){
			//System.out.println((int)(Math.random()*100));
			//System.out.println(getRandomString(10) + ',' + (int)(Math.random()*100));
			String k = getRandomString(10) + ',' + (int)(Math.random()*100 )+ ','+ "2016-09-0" + (int)(random.nextInt(9)%9 + 1)+"\r\n" ;
			//System.out.println(k);
			out.write(k.getBytes());
			String sql = ""
			pst = conn.prepareStatement(sql);
			pst
			x++;
			} 
		out.close();  
		conn.close(); 
		pst.close();
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		 

	}

}
