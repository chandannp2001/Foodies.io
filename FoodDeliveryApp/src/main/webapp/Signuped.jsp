<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="stylesheet" href="Signuped.css">
<title>Insert title here</title>
</head>
<body>
	<div class="signup-container">
		<div class="signup-header">
			<h1>SIGN UP</h1>
		</div>
		<form class="signup-form" action="signup" method="post">
			<div class="form-group">
				<label for="username">Username</label> <input type="text"
					id="username" name="username" placeholder="Enter your username"  required>
			</div>
			<div class="form-group">
				<label for="email">Email</label> <input type="email" id="email"
					name="email" placeholder="Enter your email" required>
			</div>
			<div class="form-group">
				<label for="password">Password</label> <input type="password"
					id="password" name="password" placeholder="Enter your password" required>
			</div>
			<div class="form-group">
				<label for="confirm-password">Confirm Password</label> <input
					type="password" id="confirm-password" name="confirm-password"
					placeholder="Confirm your password" required>

			</div>
			<input type="checkbox" required> <label>Accept Terms
				And Conditions</label>


			<button type="submit" class="signup-button">Signup</button>
		</form>


		<div class="social-icons">
			<a href="#" title="Login with Facebook"><i
				class="fab fa-facebook"></i></a> <a href="#" title="Login with Twitter"><i
				class="fab fa-twitter"></i></a> <a href="#" title="Login with Google"><i
				class="fab fa-google" id="Google"></i></a>
		</div>

		<a href="Loging.jsp" class="login-link">Already have an account? Login</a>
	</div>
</body>
</html>