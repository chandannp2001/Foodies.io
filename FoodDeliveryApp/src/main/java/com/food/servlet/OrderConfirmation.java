package com.food.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food.daoimpl.CartDAOImpl;
import com.food.daoimpl.OrderDAOImpl;
import com.food.daoimpl.OrderHistoryDAOImpl;
import com.food.daoimpl.OrderItemDAOImpl;
import com.food.daoimpl.UserDAOImpl;
import com.food.model.CartItem;
import com.food.model.Order;
import com.food.model.OrderHistory;
import com.food.model.OrderItem;
import com.food.model.Restaurant;
import com.food.model.User;
import com.food.utilitys.OrderIdGenerator;


@WebServlet("/orderconfirmation")
public class OrderConfirmation extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		OrderDAOImpl orderimpl = new OrderDAOImpl();
		OrderItemDAOImpl itemimpl = new OrderItemDAOImpl();
		OrderHistoryDAOImpl historyimpl = new OrderHistoryDAOImpl();
		UserDAOImpl uimpl = new UserDAOImpl();
		CartDAOImpl cart = (CartDAOImpl) session.getAttribute("cart");
		
		User user = (User)session.getAttribute("logeduser");
		user.setAddress(req.getParameter("address"));
		uimpl.updateUser(user);
		
		
		Restaurant restaurant = (Restaurant) session.getAttribute("RestaurantDetails");
		int userId = user.getUserId();
		double totalAmount = (double) session.getAttribute("TotalAount");
		
		int orderId = Integer.parseInt(OrderIdGenerator.getId());
		Order order = new Order(orderId, userId, restaurant.getRestaurantId(),
				totalAmount, "Delivered", req.getParameter("payment-method"));
		OrderHistory orderhistory = new OrderHistory(userId,orderId, totalAmount,"Delivered");
		orderimpl.addOrder(order);
		historyimpl.addOrderHistory(orderhistory);
		
		
		Map<Integer,CartItem> allitems = cart.getAllitems();
		for(CartItem ci:allitems.values()) {
			OrderItem oi = new OrderItem(orderId, ci.getItemId(), ci.getQuantity(), ci.getPrice()*ci.getQuantity());
			itemimpl.addOrderItem(oi);
		}
		
		
		
		req.getRequestDispatcher("orderConfirming.jsp").forward(req, resp);
				
		
	}
	
	
	
	

}
