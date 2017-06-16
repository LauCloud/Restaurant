package party.laucloud;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Data {
	public static ArrayList<Bean> array = new ArrayList<Bean>();

/*	static {
		// 定义数组
		String[] arr = { "001,正宗黄焖鸡,20", "002,香辣黄焖鸡,20", "003,咖喱黄焖鸡,21",
				"004,正宗黄焖排骨,38", "005,香辣黄焖排骨改改改改改,38", "006,咖喱黄焖排骨,39" };
		for (int i = 0; i < arr.length; i++) {
			String[] data = arr[i].split(",");
			Bean b = new Bean();
			b.setMenuId(data[0]);
			b.setMenuName(data[1]);
			b.setMenuPrice(data[2]);
			array.add(b);
		}
		
	}*/
	
	static{
		try {
			readMenu("menu.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 打印菜单信息
	 */
	public static void printMenu() throws IOException {
//		readMenu(array, "menu.txt");
		StringBuilder sb = new StringBuilder();

		int maxLength = 0;
		for (int i = 0; i < array.size(); i++) {
			Bean b = array.get(i);
			int length = b.getMenuName().length();
			if (length > maxLength) {
				maxLength = length;
			}
		}
		int num = 0;
		if (maxLength % 5 == 0) {
			num = maxLength / 5;
		} else {
			num = maxLength / 5 + 1;
		}

		sb.append("编号\t");
		sb.append("菜名");
		for (int i = 0; i < num; i++) {
			sb.append("\t");
		}
		sb.append("价格\t\n");
		for (int i = 0; i < array.size(); i++) {
			Bean b = array.get(i);
			sb.append(b.getMenuId()).append("\t");
			int length = b.getMenuName().length();
			int step = 0;
			if (length % 5 == 0) {
				step = num - length / 5 + 1;
			} else {
				step = num - length / 5;
			}
			sb.append(b.getMenuName());
			for (int j = 0; j < step; j++) {
				sb.append("\t");
			}
			sb.append(b.getMenuPrice()).append("\t\n");
		}
		System.out.println(sb.toString());
	}

	/*
	 * 根据编号，返回菜的对象
	 */
	public static Bean findMenu(String menuId) {
		for (int i = 0; i < array.size(); i++) {
			Bean b = array.get(i);
			if (b.getMenuId().equals(menuId)) {
				return b;
			}
		}
		return null;
	}
	
	/*
	 * 将菜单信息写入到文本文件之中
	 */
	public static void writeFile(ArrayList<Bean> arr) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("menu.txt"));

		for (int i = 0; i < arr.size(); i++) {
			Bean b = arr.get(i);
			StringBuilder sb = new StringBuilder();
			sb.append(b.getMenuId()).append(",");
			sb.append(b.getMenuName()).append(",");
			sb.append(b.getMenuPrice());
			bw.write(sb.toString());
			bw.newLine();
			bw.flush();
		}
		bw.close();
	}
	
	/*
	 * 从文本文件中加载菜单信息
	 */
	public static void readMenu(String fileName)
			throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));

		String line;
		while ((line = br.readLine()) != null) {
			String[] data = line.split(",");
			Bean b = new Bean();
			b.setMenuId(data[0]);
			b.setMenuName(data[1]);
			b.setMenuPrice(data[2]);
			array.add(b);
		}
		br.close();
	}
}