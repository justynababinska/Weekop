<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Weekop - logowanie</title>
<meta charset="UTF-8">
<meta name="vieport" content="width=device-width, initial-scale=1">
<link href="${ pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="${ pageContext.request.contextPath}/resources/css/style.css" type="text/css" rel="stylesheet">
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="#">Weekop</a>
			<button class="navbar-toggle" data-toggle="collapse"
				data-target=".navHeaderCollapse">
				<span class="glyphicon glyphicon-list"></span>
			</button>
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="#"><span class="glyphicon glyphicon-home"></span> Główna</a></li>
				<li><a href="#"><span class="glyphicon glyphicon-plus"></span> Dodaj</a></li>
				<li><a href="#"><span class="glyphicon glyphicon-user"></span> Zaloguj</a></li>
			</ul>
		</div>
	</nav>

	<div class="container">
		<div class="col-xs-6 col-xs-offset-3 col-md-4 col-md-offset-4">
			<form class="form-signin" action="j_security_check" method="post">
				<h2 class="form-signin-heading">Zaloguj się</h2>
				<input type="text" name="j_username" class="form-control" placeholder="Nazwa użytkownika" required autofocus>
			    <input type="password" name="j_password" class="form-control" placeholder="Hasło" required autofocus>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Zaloguj</button>
				<a href="#"> Zarejestruj</a>
			</form>
		</div>
	</div>

	<footer class="footer">
		<div class="container">
			<p class="navbar-text"> Weekop - developed by Justyna Babińska during <a href="http://javastart.pl">JavaStart.pl</a></p>
		</div>
	</footer>

	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>