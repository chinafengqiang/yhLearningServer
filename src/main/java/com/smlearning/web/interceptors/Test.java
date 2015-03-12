package com.smlearning.web.interceptors;


public class Test {

	public static void main(String[] args) throws Exception {
		
//		int num1 = 3;
//		int num2 = 4;
//		// 创建一个数值格式化对象
//		NumberFormat numberFormat = NumberFormat.getInstance();
//		// 设置精确到小数点后2位
//		numberFormat.setMaximumFractionDigits(2);
//		String result = numberFormat.format((float)num1/(float)num2*100);
//		System.out.println("num1和num2的百分比为:" + result + "%");
		
		
		Double localVersion = 1.03;
		
		Double Version = 1.01;
		
		if(localVersion < Version){
			System.out.println("aaa");
		}else{
			System.out.println("bb");
		}
		
	}

	

}
