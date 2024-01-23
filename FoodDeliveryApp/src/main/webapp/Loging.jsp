<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="stylesheet" href="Loging.css">
<title>Login</title>
</head>
<body>
	<div class="login-container">
		<div class="signup-header">
			<h1>Login</h1>
		</div>
		<form class="login-form" action="Login" method="post">
			<div class="form-group">
				<label for="username">Username</label> <input type="text"
					id="username" name="username" placeholder="Enter your username"
					required>
			</div>
			<div class="form-group">
				<label for="password">Password</label> <input type="password"
					id="password" name="password" placeholder="Enter your password"
					required>
			</div>
			<button type="submit" class="login-button">Login</button>
		
		</form>
		<c:if test="${not empty errorMessage}">
			<h3 style="color: red; ">${errorMessage}</h3>
			
		</c:if>
		
		<div class="social-icons">
			<a href="#" title="Login with Facebook"><i
				class="fab fa-facebook"></i></a> <a href="#" title="Login with Twitter"><i
				class="fab fa-twitter"></i></a> <a href="#" title="Login with Google"><i
				class="fab fa-google" id="Google"></i></a>
		</div>

		<a href="ForgotPassword.jsp" class="forgot-password">Forgot Password?</a>
		<p>New User?</p>
		<a href="Signuped.jsp" class="create-account"> Create Account</a>


	</div>
</body>
</html>