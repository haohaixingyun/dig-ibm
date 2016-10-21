package com.ibm.downloadtool;
import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class DownLoadTool {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DownLoadTool d = new DownLoadTool();
		d.downLoadUrl("http://finance.sina.com.cn/");

	}
        //下载页面内容
        public String downLoadUrl(String addr){
            StringBuffer sb=new StringBuffer();
            try {
                URL url=new URL(addr);
                HttpURLConnection con= (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(5000);
                con.connect();
                System.out.println(con.getResponseCode());
                if(con.getResponseCode()==200){
                    BufferedInputStream bis=new BufferedInputStream(con.getInputStream());
                    Scanner sc=new Scanner(bis,"GBK");
                    while(sc.hasNextLine()){
                        sb.append(sc.nextLine());
                    }
                    sc.close();
                }
            } catch (Exception e) {

                e.printStackTrace();
            } 
            System.out.println(sb.toString());
            return sb.toString();
        }
    }

