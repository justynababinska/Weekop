<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

  	<jsp:include page="fragment/navbar.jspf" />

	<div class="container">
		<div class="col-xs-6 col-xs-offset-3 col-md-4 col-md-offset-4">
			<form class="form-signin" action="j_security_check" method="post">
				<h2 class="form-signin-heading">Zaloguj się</h2>
				<input type="text" name="j_username" class="form-control" placeholder="Nazwa użytkownika" required autofocus>
			    <input type="password" name="j_password" class="form-control" placeholder="Hasło" required autofocus>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Zaloguj</button>
				<a href="register"> Zarejestruj</a>
			</form>
		</div>
	</div>

    <jsp:include page="fragment/footer.jspf" />

	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>