<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="login" method="post">
		<div class="input text required">
			<input name="email" type="text" required="required"
				class="minLength:7" tabindex="2" placeholder="Email" />
		</div>
		<div class="input password">
			<input
				type="password" name="password" required="required"
				class="minLength:8" tabindex="3" placeholder="Password" />
		</div>
		<div class="input signup">
			<input class="f-button" tabindex="8" type="submit"
				value="Create account" />
		</div>
	</form>
</body>
</html>