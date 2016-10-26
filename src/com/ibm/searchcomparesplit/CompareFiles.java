package com.ibm.searchcomparesplit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class CompareFiles {
	
	//public static String comparepath1="C:/Users/IBM_ADMIN/IBM/rationalsdp/workspace/DSS_DSW_IM_Prod_Support";
	//public static String comparepath2="C:/Users/IBM_ADMIN/IBM/rationalsdp/workspace/DSS_DSC_TRAIN_SWC_IM_ProdSupp";
	
	//public static String comparepath1="C:/Users/IBM_ADMIN/IBM/rationalsdp/workspace/DSS_DSW_IM_Prod_Support";
	//public static String comparepath2="C:/Users/IBM_ADMIN/IBM/rationalsdp/workspace/DSS_DSC_TRAIN_SWC_IM_ProdSupp";
    public static String comparepath2="C:/Users/IBM_ADMIN/IBM/rationalsdp/workspace/DSS_DSC_TRAIN_SWC_IM_Dev";
    public static String comparepath1="C:/Users/IBM_ADMIN/IBM/rationalsdp/workspace/DSS_DSC_TRAIN_SWC_IM_Prod";
	public static int pathlength = 0;
	static int count = 0;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Compare starting... ");
		pathlength=comparepath1.length();
		File file = new File(comparepath1);
		File[] files = file.listFiles();
		
		for(int j=0;j<files.length;j++){
			if(files[j].isFile()){
			}
			if(files[j].isDirectory()){
				allFiles(files[j]);
			}
		}
			
		System.out.println(count);	
		System.out.println("Compare ended!!! ");	
	}
	
	
	@SuppressWarnings("resource")
	public static boolean compare(File file) throws Exception{
		
		String newpathfile1 = comparepath2+file.toString().substring(pathlength);
		String newpathfile =newpathfile1.replace("#", "");
		
		if(new File(newpathfile).isFile()){
			count++;
		}else{
			System.out.println(file);
			System.out.println("not existd::"+newpathfile);
		    return false;	
		}
		
		BufferedReader br1 = null;
		BufferedReader br2 = null;
		
		br1 = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		br2 = new BufferedReader(new InputStreamReader(new FileInputStream(newpathfile)));
		String br1line=br1.readLine();
	    String br2line=br2.readLine();		
		while(br1line!=null || br2line!=null){
			
			if(br1line==null || br2line==null){
				
				//System.out.println(br1line);
				//System.out.println(br2line);
				System.out.println("unmatch::"+file);
				System.out.println("unmatch::"+newpathfile);
				
				return false;
				
			}
			
			if(br1line.trim().compareTo(br2line.trim())!=0){
				//System.out.println(br1line);
				//System.out.println(br2line);
				System.out.println("unmatch::"+file);
				System.out.println("unmatch::"+newpathfile);
				
				return false;
				
			}
			
			br1line=br1.readLine();
			br2line=br2.readLine();
		}
		br1.close();
		br2.close();
		return true;
	}
	
	public static void allFiles(File file) throws Exception{
		File[] files= file.listFiles();
		for(int i =0;i<files.length;i++){
			
			if(files[i].isDirectory()){
				
				allFiles(files[i]);
				
			}
			if(files[i].isFile()){
				
				compare(files[i]);
				
			}
		}
		
	}

}
