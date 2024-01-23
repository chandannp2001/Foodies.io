<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.food.daoimpl.RestaurantDAOImpl, java.util.Map, com.food.model.Restaurant, com.food.model.CartItem,  com.food.daoimpl.CartDAOImpl" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="OrderConfirmed.css">
    <title>Order Confirmation</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            background-color: lightgrey;
            align-items: center;
        }

        h1 {
            text-align: center;
        }

        header {
            background-color: #333;
            color: #fff;
            padding: 10px;
            text-align: center;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .header-buttons {
            display: flex;
            margin-left: 50px;
        }

        .header-buttons button {
            background-color: #d9534f;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            margin-left: 10px;
            cursor: pointer;
        }

        .header-buttons button:hover {
            background-color: #c9302c;
        }

        #app-logo {
            max-width: 100px;
        }

        .confirmation-box {
            margin: 100px 450px;
            border: 2px solid #4caf50;
            padding: 30px;
            max-width: 500px;
            text-align: center;
            align-items: center;
            background-color: #fff;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
            border-radius: 10px;
            font-size: 18px;
        }

        h2 {
            color: #4caf50;
            font-size: 28px;
            margin-bottom: 15px;
        }

        p {
            margin: 10px 0;
            color: #333;
        }

        .ordered-items {
            margin-top: 20px;
            text-align: left;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #4caf50;
            color: white;
        }

        .total-amount {
            margin-top: 15px;
            font-weight: bold;
            font-size: 20px;
        }

        .cancel-button {
            background-color: #d9534f;
            color: #fff;
            padding: 15px;
            border: none;
            cursor: pointer;
            font-size: 18px;
            margin-top: 20px;
            transition: background-color 0.3s ease;
        }

        .cancel-button:hover {
            background-color: #c9302c;
        }

        .timer {
            font-size: 16px;
            margin-top: 10px;
        }

        @media (max-width: 600px) {
            .confirmation-box {
                max-width: 90%;
            }
        }
    </style>
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
    
    <div class="confirmation-box">
        <h2>Order Confirmed</h2>
       
        <% Restaurant restaurant =  (Restaurant)session.getAttribute("RestaurantDetails");
        	
        
        %>
        <h3>Restaurant:<%=restaurant.getName() %></h3>
         <p>Order Status: Preparing</p>
        <p>Arriving In:<%=restaurant.getDeliveryTime()%> Minutes</p>
		
		
		
        <div class="ordered-items">
            <p>Items Ordered:</p>
            <table>
                <thead>
                    <tr>
                        <th>Item Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                <%
			CartDAOImpl cart = (CartDAOImpl) session.getAttribute("cart");
			
				Map<Integer, CartItem> allitems = cart.getAllitems();

				for (CartItem ci : allitems.values()) {
					
					
			%>
                    <tr>
                        <td><%=ci.getItemname()%></td>
                        <td><%=ci.getQuantity()%></td>
                        <td>&#8377;<%=ci.getPrice()%></td>
                    </tr>
                    <%} %>
                    
   
                </tbody>
            </table>
        </div>
		<% String d = (String)session.getAttribute("formatedTotalAmount"); %>
		<%-- <fmt:formatNumber type="number" maxFractionDigits="2" value="${total}" />--%>
        <p class="total-amount">Total Amount:&#8377;<%=d %> </p>
		
		<p>Payment Method:<%= request.getParameter("payment-method") %></p>
		<%session.setAttribute("cart", null); %>
        
    </div>
</body>
</html>
