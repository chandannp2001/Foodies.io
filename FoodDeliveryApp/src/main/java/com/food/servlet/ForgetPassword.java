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

import com.food.daoimpl.UserDAOImpl;
import com.food.model.User;
import com.food.utilitys.HashingPassword;


@WebServlet("/ForgetPassword")
public class ForgetPassword extends HttpServlet{

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String Username = req.getParameter("username");
		String Email = req.getParameter("Email");
		String Newpassword = req.getParameter("Newpassword");
		String Confirmpassword = req.getParameter("Confirmpassword");
		
		if(!Newpassword.equals(Confirmpassword)) {
			req.setAttribute("errorMessage", "New Password and Confirm Password should be same ");
			req.getRequestDispatcher("ForgotPassword.jsp").include(req, resp);
		}else {
			UserDAOImpl uimpl = new UserDAOImpl();
			List<User> users = uimpl.getAllUsers();

			for (User u : users) {
				if (u.getUsername().equals(Username) && u.getEmail().equals(Email)) {
					String hashedpasword = HashingPassword.hashString(Confirmpassword);
					u.setPassword(hashedpasword);
					uimpl.updateUser(u);
					req.getRequestDispatcher("Loging.jsp").forward(req, resp);
					
				}

			}
			req.setAttribute("errorMessage", "Entered Username and Email are Invalid..");
			req.getRequestDispatcher("ForgotPassword.jsp").include(req, resp);
			
		}
		
		
	}
}
