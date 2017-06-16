package party.laucloud;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tool {
	/*
	 * 实现系统录入
	 */
	public static String myScanner() {
		Scanner sc = new Scanner(System.in);
		String data = sc.nextLine();
		return data;
	}

	/*
	 * 实现价格统计
	 */
	public static double sum(Bean b) {
		double sum = 0;
		String menuNumber = b.getMenuNumber();
		String menuPrice = b.getMenuPrice();
		double number = Double.parseDouble(menuNumber);
		double price = Double.parseDouble(menuPrice);
		sum = number * price;
		return sum;
	}

	/*
	 * 打印小票
	 */
	public static void printTicket(ArrayList<Bean> arr) {
		if(arr.isEmpty()){
			System.out.println("您还没点餐，暂无小票");
			return;
		}
		int maxLength = 0;
		for (int i = 0; i < arr.size(); i++) {
			Bean b = arr.get(i);
			int length = b.getMenuName().length();
			if(length>maxLength){
				maxLength = length;
			}
		}
		int num = 0;
		if(maxLength%5==0){
			num = maxLength/5;
		}else{
			num = maxLength/5+1;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("编号\t");
		sb.append("菜名");
		for (int i = 0; i < num-"菜名".length()/5; i++) {
			sb.append("\t");
		}
		sb.append("单价\t");
		sb.append("数量\t");
		sb.append("总计\t\n");

		double allSum = 0;
		for (int i = 0; i < arr.size(); i++) {
			Bean b = arr.get(i);
			double sum = sum(b);

			sb.append(b.getMenuId() + "\t");
			int length = b.getMenuName().length();
			/*if(length<6){
				sb.append(b.getMenuName() + "\t\t");
			}else{
				sb.append(b.getMenuName() + "\t");
			}*/
			sb.append(b.getMenuName());
			int step = 0;
			if(length%5==0){
				step = num-length/5+1;
			}else{
				step = num-length/5;
			}
			for (int j = 0; j < step; j++) {
				sb.append("\t");
			}
			sb.append(b.getMenuPrice() + "\t");
			sb.append(b.getMenuNumber() + "\t");
			sb.append(sum + "\t\n");

			allSum += sum;
		}
		System.out.println(sb.toString());
		System.out.println("总计:" + allSum);
	}
}