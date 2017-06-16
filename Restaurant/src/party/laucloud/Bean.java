package party.laucloud;

public class Bean {
	private String menuId;
	private String menuName;
	private String menuPrice;
	private String menuNumber;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(String menuPrice) {
		this.menuPrice = menuPrice;
	}

	public String getMenuNumber() {
		return menuNumber;
	}

	public void setMenuNumber(String menuNumber) {
		this.menuNumber = menuNumber;
	}

	public Bean() {
		super();
	}

	public Bean(String menuId, String menuName, String menuPrice,
			String menuNumber) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.menuNumber = menuNumber;
	}
}