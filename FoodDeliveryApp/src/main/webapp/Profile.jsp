<%@page import="com.food.daoimpl.MenuDAOImpl"%>
<%@page import="com.food.daoimpl.RestaurantDAOImpl"%>
<%@page import="com.food.daoimpl.OrderDAOImpl"%>
<%@page import="com.food.model.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List, com.food.model.Restaurant, com.food.model.Order, com.food.model.Menu, com.food.model.OrderItem, com.food.model.User, com.food.daoimpl.RestaurantDAOImpl, com.food.daoimpl.MenuDAOImpl, com.food.daoimpl.OrderDAOImpl,com.food.daoimpl.OrderItemDAOImpl"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="Profile.css">
<style>
</style>
<title>User Account</title>
</head>
<body>

	<header>
		<img src="images/foodieslogo.gif" alt="App Logo" id="app-logo">
		<div class="header-buttons">
			<a href="cart"><button>Cart</button></a>
			<a href="Home"><button>Restaurants</button></a>
			<a href="Logout"><button id="logout-btn">Logout</button></a>
		</div>
	</header>


	<div class="navbar">
		<a onclick="showAccountDetails()">Account Details</a> <a
			onclick="toggleOrderHistory()">Order History</a> <a
			onclick="showProfileEdit()">Edit Profile</a>
	</div>

	<div class="content">


		<%
		User user = (User) session.getAttribute("logeduser");
		%>




		<h1>
			<strong><%=user.getUsername()%></strong>
		</h1>
		<p style="margin-left: 1000px; margin-top: -50px;">
			<strong>Email: <%=user.getEmail()%></strong>
		</p>
		<!-- Add more user information as needed -->
		
		<div class="order-history-box" id="order-history-box">
			<h2>Order History</h2>

		<%
		List<Order> allOrdersByUser = (List<Order>)request.getAttribute("allOrdersByUser");
		OrderItemDAOImpl orderitemimpl = new OrderItemDAOImpl();
		RestaurantDAOImpl restaurantimpl = new RestaurantDAOImpl();
		MenuDAOImpl menuimpl = new MenuDAOImpl();
		
		
		for (Order orders : allOrdersByUser) {
			
			int orderId = orders.getOrderId();

			int restaurantId = orders.getRestaurantId();
			Restaurant restaurant = restaurantimpl.getRestaurant(restaurantId);
			
			List<OrderItem> allorderitems = orderitemimpl.getAllOrderItemsByOrder(orderId);

			
			
				
			
		%>
		

			<div class="order-item">
				<img src=<%=restaurant.getImagePath()%> alt="Restaurant 1" >
				<div class="order-details">
					<p>
						<strong><%=restaurant.getName() %></strong>
					</p>
					<p>Order ID: <%=orders.getOrderId() %></p>
					<p>Order Date: <%=orders.getOrderDate() %></p>
					
					<table class="order-table">
						<thead>
							<tr>
								<th>Item Name</th>
								<th>Quantity</th>
								<th>Price</th>
							</tr>
						</thead>
						<tbody>
						<% for (OrderItem orderitems : allorderitems) {
							int menuId = orderitems.getMenuId();
							Menu menu = menuimpl.getMenu(menuId);
						%>
							
							<tr>
								<td><%=menu.getItemName() %></td>
								<td><%=orderitems.getQuantity() %></td>
								<td>&#8377;<%=menu.getPrice() %></td>
							</tr>
							<%} %>
							<!-- Add more rows as needed -->
						</tbody>
					</table>
					<p class="order-total">Total Amount:&#8377;<%=orders.getTotalAmount() %></p>
					<p class="order-paymentMethod">Payment Method:<%=orders.getPaymentMethod() %></p>
				</div>
			</div>
			
			<%}
			%>
			
			<!-- Add more order items as needed -->
		</div>
	</div>

	<!-- Modal for Account Details -->
	<div id="account-details-modal" class="modal">
		<div class="modal-content">
			<span class="close-modal" onclick="closeModal()">&times;</span>
			<h2>Account Details</h2>
			<p>
				<strong>User Name:</strong>
				<%=user.getUsername()%>
			</p>
			<p>
				<strong>Email:</strong>
				<%=user.getEmail()%>
			</p>
			<p>
				<strong>Address:</strong>
				<%=user.getAddress()%>
			</p>
			<!--<p>
				<strong>Account Created Date:</strong>user.getRole()e() %>
			</p>-->
		</div>
	</div>

	<!-- Form for Profile Edit -->
	<div id="profile-edit-form" class="profile-edit-form">
		<form>
			<label for="username">Username:</label> <input type="text"
				id="username" name="username" placeholder="Enter your new username"
				required> <label for="password">Password:</label> <input
				type="password" id="password" name="password"
				placeholder="Enter your new password" required> <label
				for="address">Address:</label>
			<textarea id="address" name="address"
				placeholder="Enter your new address" required></textarea>

			<button type="submit">Update Profile</button>
		</form>
	</div>

	<script>
		function toggleOrderHistory() {
			var orderHistoryBox = document.getElementById("order-history-box");
			orderHistoryBox.style.display = (orderHistoryBox.style.display === "none") ? "block"
					: "none";
		}

		function showAccountDetails() {
			var modal = document.getElementById("account-details-modal");
			modal.style.display = "flex";
		}

		function showProfileEdit() {
			var profileEditForm = document.getElementById("profile-edit-form");
			profileEditForm.style.display = "block";
		}

		function closeModal() {
			var modal = document.getElementById("account-details-modal");
			modal.style.display = "none";

			var profileEditForm = document.getElementById("profile-edit-form");
			profileEditForm.style.display = "none";
		}
	</script>
</body>
</html>