<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.fdmgroup.model.User,
				 java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page Test</title>
</head>
<body>

	<div>
		<ul>
			<%				
				for (User user : (List<User>)request.getAttribute("users")) {
			%>
			<li>
				<div>
					<p><%= user.getFullName() %></p>
				</div>
			</li>
			<%				
				}
			%>
		</ul>
	</div>
	
	<ul>
		<%				
			for (int i = 0; i < (Integer)request.getAttribute("pages"); i++) {
		%>
		<li><a href="${pageContext.request.contextPath}/users<%= i %>"><%= i + 1 %></a></li>
    	<%				
			}
		%>
    </ul>

</body>
</html>