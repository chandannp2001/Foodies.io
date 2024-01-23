package com.food.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.food.daoimpl.UserDAOImpl;
import com.food.model.User;
import com.food.utilitys.HashingPassword;


@WebServlet("/signup")
public class Signup  extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getParameter("username");
		String rawpassword = req.getParameter("password");
		String hashedpassword = HashingPassword.hashString(rawpassword);
		String email = req.getParameter("email");
		
		
		User user = new User( username, hashedpassword, email, null, "Customer");
		UserDAOImpl uimpl = new UserDAOImpl();
		uimpl.addUser(user);
		
		RequestDispatcher rd = req.getRequestDispatcher("Loging.jsp");
		rd.forward(req, resp);
		
		
	}
	
	
}
