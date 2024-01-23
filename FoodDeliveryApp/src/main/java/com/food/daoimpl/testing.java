package com.food.daoimpl;

import java.sql.*;
import java.util.List;import java.util.function.ObjLongConsumer;

import org.ietf.jgss.Oid;

import java.util.Date;

import com.food.model.Order;
import com.food.model.OrderItem;

public class testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
//		User user = new User(1,"chandann","chandan@1234","chandannnp20011@gmail.com","anaji,janthacolony","Customer");
//		UserDAOImpl uimpl = new UserDAOImpl();
//		uimpl.addUser(user);
//		User user2 = uimpl.getUser(1);
//		System.out.println(user2);
//		user2.setUsername("ravi");
//		user2.setEmail("ravi@gmail.com");
//		uimpl.updateUser(user2);
//		System.out.println(user2);
//		List<User> list = uimpl.getAllUsers();
//		for(User u:list) {
//			System.out.println(u);
//		}
//		uimpl.deleteUser(1);
		
		
		/////////////////////////
		
		
		
//		Restaurant r = new Restaurant(1,"ola","chinees",30,"jantha colony",1,4.5,true,"D:/c/");
//		RestaurantDAOImpl rimpl = new RestaurantDAOImpl();
//		rimpl.addRestaurant(r);
//		Restaurant r = rimpl.getRestaurant(1);
//		r.setName("chandan");
//		r.setRating(4.11);
//		rimpl.updateRestaurant(r);
//		System.out.println(rimpl.getRestaurant(1));
//		List<Restaurant> list1 = rimpl.getAllRestaurants();
//		for(Restaurant re:list1) {
//			System.out.println(re);
//		}
//		rimpl.deleteRestaurant(1);
		
		///////////////////////int menuId, int restaurantId, String itemName, String description, double price, boolean isAvailable)
		
//		Menu m = new Menu(1,1,"Biriyani","Hydrabadi chicken Biriyani",180.0,true);
//		MenuDAOImpl mimpl = new MenuDAOImpl();
////		mimpl.addMenu(m);
//		
//		List<Menu> menulist = mimpl.getAllMenuByRestaurant(1);
//		
//		for(Menu menu:menulist) {
//			System.out.println(menu);
//		}
//		System.out.println(mimpl.getMenu(1));
		
		
		///////////////////
		
//		Order o = new Order(102, 1, 1,null, 300.0, "Pending", "UPI");
//		OrderDAOImpl Oimpl = new OrderDAOImpl();
////		Oimpl.addOrder(o);
//		Order order = Oimpl.getOrder(100);
//		order.setPaymentMethod("Cash");
//		Oimpl.updateOrder(order);
//		
//		List<Order> Olist = Oimpl.getAllOrdersByUser(1);
//		for(Order or:Olist) {
//		System.out.println(or);
//		}
//		
//		
		
		/////////////////////////
		
//		OrderItem oi = new OrderItem(1001, 100, 1, 2, 360.0);
		OrderItemDAOImpl Oimpl = new OrderItemDAOImpl();
//		Oimpl.addOrderItem(oi);
		OrderItem item = Oimpl.getOrderItem(1001);
		item.setItemTotal(500.0);
		Oimpl.updateOrderItem(item);
		
		System.out.println(item);
		List<OrderItem> OTlist = Oimpl.getAllOrderItemsByOrder(100);
		System.out.println(OTlist);
		
	}
		
		
		
	}

