package mypackage;

import java.util.LinkedList;
import java.util.List;

public class Inventory {

	private List<Item> items;
	
	public Inventory() {
		items = new LinkedList<>();
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(int id) {
		items.removeIf(item -> item.getId() == id);
	}
	
	public Item searchItem(int id) {
		for(Item item : items) {
			if(item.getId() == id) {
				return item;
			}
		}
		return null;
	}
	
	public void updateItem(int id, double price, int qty ) {
		Item item = searchItem(id);
		if(item != null) {
			item.setQty(qty);
			item.setPrice(price);
		}
	}
	
	public List<Item> getAllItems(){
		return items;
	}
	
	public void orderItem(int id, int qty) {
		Item item = searchItem(id);
		if(item != null && item.getQty() >= qty) {
			item.setQty(item.getQty() - qty);
			item.setUnitsSold(item.getUnitsSold() + qty);
		}
	}
	
	public Item getMostSoldItem() {
		if(items.isEmpty()) {
			return null;
		}
		Item mostSold = items.get(0);
		for(Item item : items) {
			if(item.getUnitsSold() > mostSold.getUnitsSold()) {
				mostSold = item;
			}
		}
		
		if(mostSold.getUnitsSold() == 0) {
			return null;
		}
		
		return mostSold;
	}
	
}
