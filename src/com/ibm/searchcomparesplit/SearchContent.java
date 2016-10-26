package com.ibm.searchcomparesplit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class SearchContent {

	static File folderpath =new File("C:\\Users\\IBM_ADMIN\\IBM\\rationalsdp\\workspace\\SWC_IM_Dev_DSS");
	///DSS_Production
	///ODS_Production
	static String code ="AVG_SALE_PRICE_REF";
	
	public static void main(String[] args) {
    
		System.out.println("Starting....");
		
		File[] filelist = folderpath.listFiles();
		System.out.print(filelist.length);
		for(int i = 0 ;i <filelist.length;i++){
			if(filelist[i].isFile()){
				findContent(filelist[i],code);
				
			}
			if(filelist[i].isDirectory()){
				listFiles(filelist[i]);
			}
		}
		
		System.out.println("end....");
	}
	
	
	private static void listFiles(File f){
		
		File[] filelist=f.listFiles();
		
		for(int j=0;j<filelist.length;j++){
		
		if(filelist[j].isFile()){
			findContent(filelist[j],code);
			
		}
		if(filelist[j].isDirectory()){
			listFiles(filelist[j]);
			//System.out.println(filelist[j]);
		}
		
		}
	}
    
	private static void findContent(File file,String content){
		
		try {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));;
		String str = null;
		
			while((str=br.readLine()) != null){
				if(str.toLowerCase().indexOf(content.toLowerCase())>=0){
					System.out.println(file);
					return ;
				};
				
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 catch (IOException e) {
			e.printStackTrace();
		}
		 
	 }
}
