<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="login" method="post">
		<div>
			<input name="email" type="text" 
			required="required" placeholder="Email" />
		</div>
		<div>
			<input type="password" name="password" 
			required="required" placeholder="Password" />
		</div>
		<div class="input signup">
			<input type="submit" />
		</div>
	</form>
</body>
</html>