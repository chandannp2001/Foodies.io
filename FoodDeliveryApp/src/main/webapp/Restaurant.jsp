
<%@page import="com.food.model.User"%>
<%@ page language="java" contentType="text/html;  charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List, com.food.daoimpl.RestaurantDAOImpl, com.food.model.Restaurant, com.food.model.User"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="Restaurant.css">
<title>Home Page</title>
</head>
<body>

	<header>
		<img src="images/foodieslogo.gif" alt="App Logo" id="app-logo">
		<div class="header-buttons">
			<a href="cart"><button>Cart</button></a>
			<a href="Profile"><button>Account</button></a>
			<a href="Logout"><button id="logout-btn">Logout</button></a>
		</div>
	</header>

	

	
	
	<%User u = (User)session.getAttribute("logeduser");
		
	%>
	
	<h1>Welcome <%=u.getUsername() %> </h1>
	<h1>Restaurants</h1>

	<div class="container">

		<%
		List<Restaurant> list = (List<Restaurant>) request.getAttribute("restaurants");

		for (Restaurant restaurant : list) {

			String name = restaurant.getName();
			double rating = restaurant.getRating();
			String CuisineType = restaurant.getCuisineType();
			String ImagePath = restaurant.getImagePath();
			int eta = restaurant.getDeliveryTime();
		%>
		<div class="restaurant-card">
			<a href="menu?RestaurantId=<%=restaurant.getRestaurantId()%>">
				<div class="restaurant-details">
					<img src=<%=ImagePath%> alt="Restaurant " width="110%">
			</a>
			<h2>
				<%=name%>
			</h2>
			<p class="rating">
				Ratings:<%=rating%></p>
			<p>
				Cuisine Type:<%=CuisineType%></p>
			<p>
				Estimated Delivery Time:<%=eta%></p>
			</a>
		</div>
	</div>


	<%
	}
	%>


	</div>

</body>
</html>
