package com.ibm.enums;

public class TestEnum {
	
	public enum ColorEnums{
		
		red ,green ,yellow,blue;
	}
	
	public enum Male{
		
		ForMale("girl",1),Male("boy",2);
		
		private String name;
		private int index;
		
	    public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
		
		//// 构造方法
		private Male(String name,int index){
	    	
	    	this.name = name;
	    	this.index = index ;
	    }
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//遍历枚举
		for(ColorEnums ce : ColorEnums.values()){  //此处的values 是 变量本身
			System.out.println(ce);
		}
		//赋值时通过“枚举名.值”取得枚举中的值
		ColorEnums a = ColorEnums.green;
		switch(a){
		case red:
			System.out.println("this is red");
			break;
		case green:
			System.out.println("this is green");
			break;
		case yellow:
			System.out.println("this is yellow");
			break;
			
		}
		
		//计算枚举的个数 
		
		System.out.println("ColorEnums 枚举个数： "+ ColorEnums.values().length);
		
		//计算枚举的index
		
		System.out.println(ColorEnums.blue.ordinal()) ; //blue location is 3 ,so it looks like bigan from 0 
		
		//枚举方法中实现了 compare 方法
		
		System.out.println(ColorEnums.blue.compareTo(ColorEnums.green)); //2 
		
		//更复杂的枚举  Male
		
		for(Male m : Male.values()){
			
			System.out.println(m.index +":"+ m.name); //m.values()  是一个对象 
			System.out.println();
		}

	}

}
