<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="jquery-2.1.4.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script type="text/javascript">
     $(document).on("click", "#somebutton", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
         $.post("menu", function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
             $("#somediv").text(responseText);           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
         });
     });
</script>

<body>
	<c:if test="${sessionScope.categories != null}">
			<c:forEach var="categories" items="${sessionScope.categories}"> 	
				<c:out value="${ categories }"></c:out>
       			 <div id="somediv"></div>
       			 <form action="menu" method="post">
					<button id="somebutton" onclick="menu()" name="category" value="${ categories }" > Show menu</button>
				</form>
			</c:forEach>
	</c:if>
<br>
<h3>Results:</h3>
<h2 id="taskNumber"></h2>
<p id= "taskResponse"></p>
</body>
</html>