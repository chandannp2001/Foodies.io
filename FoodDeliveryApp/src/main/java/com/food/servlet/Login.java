package com.food.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food.daoimpl.UserDAOImpl;
import com.food.model.User;
import com.food.utilitys.HashingPassword;

@WebServlet("/Login")
public class Login extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getParameter("username");
		String unhasedpassword = req.getParameter("password");
		
		String hashedPassword = HashingPassword.hashString(unhasedpassword);

		UserDAOImpl uimpl = new UserDAOImpl();
		List<User> users = uimpl.getAllUsers();

		for (User u : users) {
			if (u.getUsername().equals(username) && u.getPassword().equals(hashedPassword)) {

				HttpSession session = req.getSession();
				session.setAttribute("logeduser", u);
				RequestDispatcher rd = req.getRequestDispatcher("Home");

				rd.include(req, resp);

			}

		}

		req.setAttribute("errorMessage", "Entered Credential are Invalid");

		RequestDispatcher rd2 = req.getRequestDispatcher("Loging.jsp");
		rd2.include(req, resp);

	}

}
