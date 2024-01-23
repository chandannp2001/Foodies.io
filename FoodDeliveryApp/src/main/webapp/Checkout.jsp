<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.LinkedHashMap, java.util.Map, com.food.model.CartItem,  com.food.daoimpl.CartDAOImpl"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="Checkout.css">
<title>Checkout</title>
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

	<h1>CHECKOUT</h1>
	<section>
		<div class="item-box">
			<div class="item-box-header">
				<h3 style="color: white-space; margin-left: 40%;">Food Details</h3>
			</div>

			<%
			CartDAOImpl cart = (CartDAOImpl) session.getAttribute("cart");
			double totalAmount = 0.0;
			if (cart != null) {

				Map<Integer, CartItem> allitems = cart.getAllitems();

				for (CartItem ci : allitems.values()) {
					double price = ci.getPrice();
					int quantity = ci.getQuantity();
					double totalPrice = price * quantity;
			%>


			<div class="item-details">
				<p style="color: black;">
					<strong>Item Name:<%=ci.getItemname()%></strong>
				</p>
				<p style="color: black;">
					<strong>Price:&#8377;<%=price%></strong>
				</p>
			</div>
			<div class="item-details">
				<p style="color: black;">
					<strong>Quantity:<%=quantity%></strong>
				</p>
				<p style="color: black;">
					<strong>Total Price:&#x20B9;<%=totalPrice%></strong>
				</p>
			</div>
			<div class="item-separator"></div>

			<%
			totalAmount += totalPrice; //'UPI', 'Cash', 'Debit Card', 'Credit Card
			}
			}
			
			String GSTamount = String.format("%.2f", totalAmount*0.08);
			double totalamount = (totalAmount * (0.08)) + 52 + totalAmount;
			String formatedTotalAmount =  String.format("%.2f", totalamount);
			%>

		</div>

		<div class="total-amount" >
			<div class="item-box-header"
				style="margin: -20px; margin-bottom: 10px">
				<h3 style="color: #333; margin-left: 40%;">Bill Details</h3>
			</div>

			<!-- Additional Information -->
			<div class="item-details">
				<p style="color: black;">
					<strong>Item Total:</strong>
				</p>
				<p style="color: black;">
					<strong>&#8377;<%=totalAmount%></strong>
				</p>
			</div>
			<div class="item-details">
				<p style="color: black;">
					<strong>Packaging Charges:</strong>
				</p>
				<p style="color: black;">
					<strong>&#8377;10.78</strong>
				</p>
			</div>

			<div class="item-details">
				<p style="color: black;">
					<strong>GST:</strong>
				</p>
				<p style="color: black;">
					<strong>&#8377;<%=GSTamount%></strong>
				</p>
			</div>

			<div class="item-details">
				<p style="color: black;">
					<strong>Delivery Charge:</strong>
				</p>
				<p style="color: black;">
					<strong>&#8377;42.69</strong>
				</p>
			</div>

			<div class="item-separators"></div>

			<div class="item-details">
				<p style="color: black;">
					<strong><b>Amount to Pay:</b></strong>
				</p>
				<p style="color: black;">
					<strong><b>&#8377;<%=formatedTotalAmount%></b></strong>
				</p>

			</div>
			<%
			session.setAttribute("TotalAount",totalamount);
			session.setAttribute("formatedTotalAmount", formatedTotalAmount);
			%>
		
		</div>


		<div class="payment-form">
		<form action="orderconfirmation" method="post">
			<label for="payment-method" style="color: black;">Payment
				Method:</label> <select id="payment-method" name="payment-method"
				required="required">
				<option value="UPI">UPI</option>
				<option value="Debit Card">Debit Card</option>
				<option value="Credit Card">Credit Card</option>
				<option value="Cash">Cash On Delivery</option>
				<!-- Add more options as needed -->
			
			</select> <label for="address" style="color: black;">Delivery Address:</label>
			<input type="text" id="address" name="address"
				placeholder="Enter your address" required>

			<button class="submit-btn" type="submit">Order</button>
			</form>
		</div>
	</section>


</body>
</html>