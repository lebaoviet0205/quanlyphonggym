<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Login</title>
<link href="resources/css/login.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
</head>
<body>
	<div class="container">
		<div class="nav">
			<a href="home.htm" class="back-btn"> <i
				class="fa-solid fa-chevron-left"></i>
			</a>
		</div>
		<div class="wrapper">
			<div class="login-image">
				<img src="resources/img/carousel-2.jpg" />
			</div>
			<div class="login-form">
				<div class="header">
					<h1 class="title">LOGIN</h1>
				</div>
				<form action="login.htm" method="post" class="form">
					<div class="form-group">
						<label class="form-label"> <span class="label">Username</span>
							<input type="text" name="username" class="input" required="required" value="${username}">
						</label>
					</div>
					<div class="form-group">
						<label class="form-label"> <span class="label">Password</span>
							<input type="password" name="password" class="input" required="required" value="${username}">
						</label>
					</div>
					<div class="remember-forgot">
						<label for="chk1" class="remember"> <input id="chk1"
							type="checkbox" name="chk"> Remember me
						</label> <a href="#" class="forgot">Forgot Password?</a>
					</div>
					<div class="form-group">
						<img src="${pageContext.request.contextPath}/captcha"> <br>
						<label class="form-label">
							<span class="label">Please Enter Captcha</span>
							<input type="text" name="captcha" required="required" class="input">
						</label>
						
					</div>
					<div class="error-message">
						${error}
					</div>
					<div class="submit-btn-container">
						<input type="submit" value="Login">
					</div>
				</form>
			</div>
		</div>
		<div class="overlay hide"></div>
		<div class="modal hide">
			<h2>Forgot Password?</h2>
			<img alt="Forgot Password" src="resources/img/confused.png">
			<p>Sorry. Please contact your manager for more information!</p>
			<button class="hide-modal">OK</button>
		</div>
	</div>

	<script>
		const inputs = document.querySelectorAll(".input");
		inputs.forEach((input)=> {
			input.addEventListener("focus", ()=>{
				input.previousElementSibling.classList.add("active");
			})
			
			input.addEventListener("focusout", (e)=>{
				const label = input.previousElementSibling;
				if (label.classList.contains("active")){
					label.classList.remove("active");
				}
			})
			
			input.addEventListener("input", (e)=>{
				const text = e.target.value;
				const label = input.previousElementSibling;
				if (text !== ""){
					label.classList.add("hide");
				} else {
					label.classList.remove("hide");
				}
			})
		})
	</script>
	<script>
		const forgot = document.querySelector(".forgot");
		const hideModal = document.querySelector(".hide-modal");
		const modal = document.querySelector(".modal");
		const overlay = document.querySelector(".overlay");
		
		forgot.addEventListener("click", ()=>{
			modal.classList.toggle("hide");
			overlay.classList.toggle("hide");
		});
		hideModal.addEventListener("click", ()=>{
			modal.classList.toggle("hide");
			overlay.classList.toggle("hide");
		})
	</script>
	<script>
		//const inputs = document.querySelectorAll(".input");
		const error = document.querySelector(".error-message");
		inputs.forEach((input)=>{
			input.addEventListener("focus", () => {
				error.innerText = "";
			})
		})
	</script>
</body>