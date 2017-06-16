package party.laucloud;

import java.io.IOException;
import java.util.ArrayList;

public class Test {
	public static void main(String[] args) throws IOException {
		ArrayList<Bean> arr = new ArrayList<Bean>();
		System.out.println("---欢迎使用小星星点餐系统---");
		System.out.println("1.管理员登录");
		System.out.println("2.客户点餐");
		System.out.println("3.退出");
		System.out.print("请输入您的选择:");
		String choice = Tool.myScanner();
		if ("1".equals(choice)) {
			for (int i = 0; i < 3; i++) {
				System.out.print("用户名:");
				String user = Tool.myScanner();
				System.out.print("密码:");
				String password = Tool.myScanner();

				if (user.isEmpty() || password.isEmpty()) {
					if (2 - i != 0) {
						System.out.println("用户名或密码不能为空！请重新输入");
						System.out.println("您还有" + (2 - i) + "次机会!");
					} else {
						System.out.println("用户名或密码不能为空！");
						System.out.println("您的机会已用完，该账号已被锁定，请联系管理员！");
						System.exit(0);
					}
				} else if (!("admin".equalsIgnoreCase(user))
						|| !("123".equalsIgnoreCase(password))) {
					if (2 - i != 0) {
						System.out.println("用户名或密码错误！请重新输入");
						System.out.println("您还有" + (2 - i) + "次机会!");
					} else {
						System.out.println("用户名或密码错误！");
						System.out.println("您的机会已用完，该账号已被锁定，请联系管理员！");
						System.exit(0);
					}
				} else {
					System.out.println("登陆成功");
					break;
				}
			}
			while (true) {
				System.out.println("请选择进一步操作");
				System.out.println("1.查看所有菜式");
				System.out.println("2.增加菜式");
				System.out.println("3.删除菜式");
				System.out.println("4.修改菜式");
				System.out.println("5.退出");
				System.out.print("您的选择:");
				String menuChoice = Tool.myScanner();
				switch (menuChoice) {
				case "1":
					System.out.println("查看所有菜式");
					showAllItems();
					break;
				case "2":
					System.out.println("增加菜式");
					addItem();
					break;
				case "3":
					System.out.println("删除菜式");
					removeItem();
					break;
				case "4":
					System.out.println("修改菜式");
					changeItem();
					break;
				case "5":
				default:
					System.out.println("谢谢使用！");
					System.exit(0);
				}
//				Data.readMenu("menu.txt");
			}
		} else if ("2".equals(choice)) {
			System.out.println();
			Data.printMenu();

			while (true) {
				System.out.println("请选择你的操作(1.点餐,2.结账并退出):");
				String option = Tool.myScanner();
				if ("1".equals(option)) {
					order(arr);
				} else {
					Tool.printTicket(arr);
					System.out.println("谢谢惠顾！");
					break;
				}
			}
		} else {
			System.out.println("谢谢您的使用！");
			System.exit(0);
		}

		/*
		 * System.out.println(); Data.printMenu();
		 * 
		 * while (true) { System.out.println("请选择你的操作(1.点餐,2.结账并退出):"); String
		 * option = Tool.myScanner(); if ("1".equals(option)) { order(arr); }
		 * else { Tool.printTicket(arr); System.out.println("谢谢惠顾！"); break; } }
		 */
	}

	private static void changeItem() throws IOException {
		ArrayList<Bean> arr = Data.array;
		String id;
		while(true){
			System.out.println("请输入要修改菜式的编号:");
			id = Tool.myScanner();
			if(id.isEmpty()){
				System.out.println("要修改的菜式id不能为空，请重新输入");
			}else{
				break;
			}
		}
		int index=-1;
		for (int i = 0; i < arr.size(); i++) {
			Bean b = arr.get(i);
			String menuId = b.getMenuId();
			if(menuId.equals(id)){
				index = i;
			}
		}
		if(index==-1){
			System.out.println("要修改的菜式id不存在，请重新选择操作");
		}else{
			
			String name;
			while (true) {
				System.out.println("请输入菜式的新名称:");
				name = Tool.myScanner();
				if (name.isEmpty() || name.contains(",")) {
					if (name.isEmpty()) {
						System.out.println("菜式名称不能为空，请重新输入");
						continue;
					}
					if(name.contains(",")){
						System.out.println("菜式名称不能包含逗号，请重新输入");
						continue;
					}
				}else{
					break;
				}
			}
			
			
			String price;
			while (true) {
				System.out.println("请输入菜式的新价格:");
				price= Tool.myScanner();
				if (price.isEmpty() || price.contains(",")) {
					if (price.isEmpty()) {
						System.out.println("菜式价格不能为空，请重新输入");
						continue;
					}
					if(price.contains(",")){
						System.out.println("菜式价格不能包含逗号，请重新输入");
						continue;
					}
				}else{
					break;
				}
			}
			
			Bean d = new Bean();
			d.setMenuId(id);
			d.setMenuName(name);
			d.setMenuPrice(price);
			arr.set(index, d);
			System.out.println("修改成功");
			Data.array = arr;
			Data.writeFile(arr);
		}
	}

	private static void removeItem() throws IOException {
		ArrayList<Bean> arr = Data.array;
		String id;
		while(true){
			System.out.println("请输入要删除菜式的编号:");
			id = Tool.myScanner();
			if(id.isEmpty()){
				System.out.println("要删除的菜式id不能为空，请重新输入");
			}else{
				break;
			}
		}
		int index = -1;
		for (int i = 0; i < arr.size(); i++) {
			Bean b = arr.get(i);
			String menuId = b.getMenuId();
			if (menuId.equals(id)) {
				index = i;
			}
		}
		if(index==-1){
			System.out.println("您输入的菜式id不存在，请选择其它操作");
		}else{
			arr.remove(index);
			System.out.println("删除菜式成功");
		}
		Data.array = arr;
		Data.writeFile(arr);
	}

	private static void addItem() throws IOException {
		ArrayList<Bean> arr = Data.array;
		String id;
		while (true) {
			System.out.println("请输入新菜式编号:");
			id = Tool.myScanner();
			if (id.isEmpty() || id.contains(",")) {
				if (id.isEmpty()) {
					System.out.println("菜式编号不能为空，请重新输入！");
					continue;
				}
				if (id.contains(",")) {
					System.out.println("菜式编号不能包含逗号，请重新输入！");
					continue;
				}
			}
			boolean exit = false;
			;
			for (int i = 0; i < arr.size(); i++) {
				Bean a = arr.get(i);
				String menuId = a.getMenuId();
				if (menuId.equals(id)) {
					exit = true;
				}
			}
			if (exit) {
				System.out.println("您输入的菜式编号已经存在，请重新输入");
			} else {
				break;
			}
		}
		String name;
		while (true) {
			System.out.println("请输入新菜式名称:");
			name = Tool.myScanner();
			if (name.isEmpty() || name.contains(",")) {
				if (name.isEmpty()) {
					System.out.println("菜式名称不能为空，请重新输入！");
					continue;
				}
				if (name.contains(",")) {
					System.out.println("菜式名称不能包含逗号，请重新输入！");
					continue;
				}
			}
			boolean exit2 = false;
			for (int i = 0; i < arr.size(); i++) {
				Bean c = arr.get(i);
				String menuName = c.getMenuName();
				if (menuName.equals(name)) {
					exit2 = true;
				}
			}
			if (exit2) {
				System.out.println("您输入的菜式名称已经存在，请重新输入");
			} else {
				break;
			}
		}
		String price;
		while (true) {
			System.out.println("请输入新菜式价格:");
			price = Tool.myScanner();
			if (price.isEmpty() || price.contains(",")) {
				if (price.isEmpty()) {
					System.out.println("菜式价格不能为空，请重新输入！");
					continue;
				}
				if (price.contains(",")) {
					System.out.println("菜式价格不能包含逗号，请重新输入！");
					continue;
				}
			} else {
				break;
			}
		}
		Bean b = new Bean();
		b.setMenuId(id);
		b.setMenuName(name);
		b.setMenuPrice(price);
		arr.add(b);
		System.out.println("添加成功！");
		Data.array = arr;
		Data.writeFile(arr);
	}

	private static void showAllItems() throws IOException {
		ArrayList<Bean> arr = Data.array;
		if (arr.size() == 0) {
			System.out.println("抱歉，暂时还没有菜式添加进来");
			return;
		}
		Data.printMenu();
	}

	/*
	 * 具体点餐
	 */
	public static void order(ArrayList<Bean> arr) {
		System.out.println("请输入你选择的菜式编号:");
		String xId = Tool.myScanner();
		System.out.println("请输入你选择的菜式数量:");
		String xNumber = Tool.myScanner();
		Bean b = Data.findMenu(xId);
		b.setMenuNumber(xNumber);
		arr.add(b);
	}
}