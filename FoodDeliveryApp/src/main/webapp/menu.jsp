<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List, com.food.daoimpl.MenuDAOImpl, com.food.model.Menu, com.food.model.Restaurant"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="menu.css">
<title>Food Delivery App</title>
</head>
<body>

	<header>
		<img src="images/foodieslogo.gif" alt="App Logo" id="app-logo">
		<div class="header-buttons">
			<a href="Home"><button>Restaurants</button></a> <a href="cart"><button>Cart</button></a>
			<a href="Profile"><button>Account</button></a>
			<a href="Logout"><button id="logout-btn">Logout</button></a>
		</div>
	</header>
	<%Restaurant r =(Restaurant)session.getAttribute("RestaurantDetails"); %>
	<div class="container">
		<div class="left-side">
			<h2>Restaurant:<%=r.getName() %></h2>
			<h4><%=r.getCuisineType() %></h4>
			<p>Delivery Time:<%=r.getDeliveryTime() %> minutes</p>
			<p>Address:<%=r.getAddress() %></p>
		</div>
		<div class="right-side">
			<p class="rating">&#9733;<%=r.getRating() %></p>
			<!-- Star symbol entity -->
		</div>
	</div>

	<h1>
		Menu
	</h1>

	<section class="menu">


		<%
		List<Menu> list = (List<Menu>) request.getAttribute("listmenu");

		for (Menu menu : list) {
			int menuid = menu.getMenuId();
			String itemname = menu.getItemName();
			double price = menu.getPrice();
			String Description = menu.getDescription();
		%>



		<div class="menu-card">
			<img src="images/r1.jpeg" alt="Food Item 1">
			<h2>
				<%=itemname%></h2>
			<p class="price"><%=price%>rs
			</p>
			<p class="description"><%=Description%></p>
			<form action="cart" method="post">
				<input type="hidden" name="itemId" value="<%=menuid%>"> <label
					for="quantity">Quantity: <input type="number" id="quantity"
					name="quantity" value="1" min="1"></br>
				</label></br> <input type="submit" name="action" value="Add To Cart"
					class="add-cart">
			</form>

		</div>

		<%
		}
		%>


	</section>
</body>
</html>
