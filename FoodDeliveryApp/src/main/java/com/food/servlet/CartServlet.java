package com.food.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;

import com.food.daoimpl.CartDAOImpl;
import com.food.daoimpl.MenuDAOImpl;
import com.food.model.CartItem;
import com.food.model.Menu;
import com.food.model.User;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		CartDAOImpl cart = (CartDAOImpl) session.getAttribute("cart");

		if (cart == null) {
			cart = new CartDAOImpl();
			session.setAttribute("cart", cart);
		}

		String action = req.getParameter("action");

		if (action.equals("Add To Cart")) {
			addItemToCart(req, cart);

		} else if (action.equals("Update")) {
			updateCartItem(req, cart);

		} else if (action.equals("Remove")) {
			removeCartItem(req, cart);
		}

		session.setAttribute("cart", cart);
		RequestDispatcher rd = req.getRequestDispatcher("foodcart.jsp");
		rd.forward(req, resp);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("foodcart.jsp");
		rd.include(req, resp);
	}

	private void addItemToCart(HttpServletRequest req, CartDAOImpl cart) {

		int itemId = Integer.parseInt(req.getParameter("itemId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));

		MenuDAOImpl mimpl = new MenuDAOImpl();
		Menu menu = mimpl.getMenu(itemId);
		HttpSession session = req.getSession();

		if (cart.getAllitems().isEmpty()) {
			session.setAttribute("cartRestaurantId", menu.getRestaurantId());
		}

		if (menu.getRestaurantId() == (Integer) session.getAttribute("cartRestaurantId")) {

			CartItem item = new CartItem(menu.getMenuId(), menu.getRestaurantId(), menu.getItemName(), menu.getPrice(),
					quantity);
			cart.addItem(item);

		}
		else {
			req.setAttribute("errorMessages", "Cannot Add Items From Different Restaurant..");

		}
	}

	private void updateCartItem(HttpServletRequest req, CartDAOImpl cart) {

		int itemId = Integer.parseInt(req.getParameter("itemId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));

		cart.updateItem(itemId, quantity);

	}

	private void removeCartItem(HttpServletRequest req, CartDAOImpl cart) {

		int itemId = Integer.parseInt(req.getParameter("itemId"));

		cart.removeItem(itemId);
	}
}
