package com.food.daoimpl;

import java.util.LinkedHashMap;

import java.util.Map;

import com.food.model.CartItem;

public class CartDAOImpl {
	
	Map<Integer,CartItem> allitems;
	
	public CartDAOImpl() {
		allitems = new LinkedHashMap<Integer, CartItem>();
		
	}
	
	public void clear() {
		allitems.clear();
	}
	
	public Map<Integer,CartItem> getAllitems(){
		return allitems;
	}
	
	public void removeItem(int itemId) {
		
		allitems.remove(itemId);
	}
	
	public void updateItem(int itemId,int quantity) {
		
		if(allitems.containsKey(itemId)) {
			if(itemId<=0) {
				allitems.remove(itemId);
			}
			else {
				allitems.get(itemId).setQuantity(quantity);
				
			}
		}
		
	}
	
	public void addItem(CartItem ci) {
		
		int itemId = ci.getItemId();
		if(allitems.containsKey(itemId)) {
			CartItem existingitem = allitems.get(itemId);
			existingitem.setQuantity(ci.getQuantity()+existingitem.getQuantity());
		}
		else {
			allitems.put(itemId, ci);
		}
	}

}
