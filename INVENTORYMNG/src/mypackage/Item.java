package mypackage;

public class Item {

	private int id;
	private String name;
	private int qty;
	private double price;
	private int unitsSold;
	
	public Item(int id, String name, double price, int qty) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.qty = qty;
		this.unitsSold = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getUnitsSold() {
		return unitsSold;
	}

	public void setUnitsSold(int unitsSold) {
		this.unitsSold = unitsSold;
	}
	
	
	public String toString() {
		return "ID: "+id+", Name: "+name+", Quantity: "+qty+", Price: "+price+", Units Sold: "+unitsSold;
	}
	
}
