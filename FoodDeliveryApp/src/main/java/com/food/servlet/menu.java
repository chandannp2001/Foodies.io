package com.food.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food.daoimpl.MenuDAOImpl;
import com.food.daoimpl.RestaurantDAOImpl;
import com.food.model.Menu;
import com.food.model.Restaurant;
import com.mysql.cj.Session;

@WebServlet("/menu")
public class menu extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int RestaurantId = Integer.parseInt(req.getParameter("RestaurantId"));
		HttpSession session = req.getSession();
		session.setAttribute("RestaurantId", RestaurantId);
		
		
		RestaurantDAOImpl rimpl = new RestaurantDAOImpl();
		Restaurant restaurant = rimpl.getRestaurant(RestaurantId);
		String rname = restaurant.getName();
		
		session.setAttribute("RestaurantDetails", restaurant);		
		
		MenuDAOImpl menuImpl = new MenuDAOImpl();
		List<Menu> listmenu = menuImpl.getAllMenuByRestaurant(RestaurantId);
		
		req.setAttribute("listmenu",listmenu);
		
		RequestDispatcher rd = req.getRequestDispatcher("menu.jsp");
		rd.include(req, resp);
		
	}
	
}
