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
<link rel="stylesheet" href="ForgetPassword.css">
<title>Insert title here</title>
</head>
<body>
	<div class="login-container">
		<div class="signup-header">
			<h1>Create New Password</h1>
		</div>
		<form class="login-form" action="ForgetPassword" method="post">
			<div class="form-group">
				<label for="username">Username</label> <input type="text"
					id="username" name="username" placeholder="Enter your username"
					required>
			</div>
			<div class="form-group">
				<label for="Email">Email</label> <input type="text"
					id="Email" name="Email" placeholder="Enter your Email"
					required>
			</div>
			<div class="form-group">
				<label for="password">New Password</label> <input type="password"
					id="password" name="Newpassword" placeholder="Enter your New password"
					required>
			</div>
			<div class="form-group">
				<label for="Confirmpassword">Confirm Password</label> <input type="password"
					id="Confirmpassword" name="Confirmpassword" placeholder="Enter your New password"
					required>
			</div>
			<button type="submit" class="login-button">Submit</button>
		
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

		<a href="Loging.jsp" class="login-link">Already have an account? Login</a>
		<p>New User?</p>
		<a href="Signuped.jsp" class="create-account"> Create Account</a>


	</div>
</body>
</html>