package com.food.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.food.daoimpl.RestaurantDAOImpl;
import com.food.model.Restaurant;

@WebServlet("/Home")
public class Home extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		RestaurantDAOImpl impl = new RestaurantDAOImpl();
		List<Restaurant> restaurants = impl.getAllRestaurants();
		
		req.setAttribute("restaurants", restaurants);
		
		RequestDispatcher rd = req.getRequestDispatcher("Restaurant.jsp");
		rd.forward(req, resp);
		
		
		
	}
	
}
