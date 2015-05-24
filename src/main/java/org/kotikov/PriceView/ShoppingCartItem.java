package org.kotikov.PriceView;

import org.kotikov.framework.PriceUtils;

public class ShoppingCartItem {

	private String itemTitle;
	private String ItemOldPrice;
	private String ItemActualCost;
	private String place;
	
	private final String  placeStart =  "//li[@class=\"price-cart__item\"][";  
	private final String  placeEnd   = "]/small";
	
	
	public String getItemTitle() {
		return itemTitle;
	}

	public String getItemOldPrice() {
		return ItemOldPrice;
	}

	public String getItemActualCost() {
		return ItemActualCost;
	}


	public String getPlace() {
		return place;
	}

	 
	@Override
	public String toString() {
		return "ShoppingCartItem [itemTitle=" + itemTitle + ", ItemOldPrice="
				+ ItemOldPrice + ", ItemActualCost=" + ItemActualCost
				+ ", place=" + place + "]";
	}

	public void print() {
		System.out.println(toString());
	}

	public ShoppingCartItem(String itemTitle, String itemOldPrice,
			String itemActualCost, int pl) {
		 
		this.itemTitle = itemTitle;
		this.ItemOldPrice = PriceUtils.parsCurrency(itemOldPrice);
		this.ItemActualCost = PriceUtils.parsCurrency(itemActualCost);
		this.place = placeStart + Integer.toString(pl+1) + placeEnd ;
	};
}
