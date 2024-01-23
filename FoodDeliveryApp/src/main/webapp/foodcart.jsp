<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.LinkedHashMap, java.util.Map, com.food.model.CartItem,  com.food.daoimpl.CartDAOImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="foodcart.css">
<title>Food Cart</title>
</head>
<body>


	<header>
		<img src="images/foodieslogo.gif" alt="App Logo" id="app-logo">
		<div class="header-buttons">
			<a href="Home"><button>Restaurants</button></a>
			<a href="Profile"><button>Account</button></a>
			<a href="Logout"><button id="logout-btn">Logout</button></a>
		</div>
	</header>

	<h1>Foodies Cart</h1>
	<div class="container">
		<main>

			<%
			CartDAOImpl cart = (CartDAOImpl) session.getAttribute("cart");
			if (cart != null) {

				Map<Integer, CartItem> allitems = cart.getAllitems();

				for (CartItem ci : allitems.values()) {
			%>

			<div class="food-item">
				<h2>
					Item Name:<%=ci.getItemname()%></h2>
				<p>
					Price:<%=ci.getPrice()%></p>
				<form action="cart" method="post">

					<label for="quantity">Quantity: <input type="number"
						id="quantity" name="quantity" value="<%=ci.getQuantity()%>"
						min="1"> 
						<input type="hidden" name="itemId"value="<%=ci.getItemId()%>">
						
					</label> <input type="submit" name="action" value="Update"
						class="update-btn"> <input type="submit" name="action"
						value="Remove" class="remove-btn">
				</form>
			</div>

			<%
			}
			%>
		</main>

	</div>
	
	<c:if test="${not empty errorMessages}">
			<h2 style="color: blue; margin-left: 21%">${errorMessages}</h2>
			
		</c:if>
		
	<a href="menu?RestaurantId=<%=session.getAttribute("RestaurantId")%>"><button
			class="update-btn" id="addmore">Add More Items</button></a>


	<form action="Checkout.jsp" method="post">
		<input type="submit" name="" value="Proceed to Checkout"
			class="remove-btn" id="checkout">
	</form>
	<%
	} else {
	%>

	<h2>Cart is Empty..Please add some items.</h2>

	<%
	}
	%>


</body>
</html>