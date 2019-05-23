<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Crunchify - Upload Multiple Files Example</title>
<style type="text/css">
body {
    background-image:
        url('https://cdn.crunchify.com/wp-content/uploads/2013/03/Crunchify.bg_.300.png');
}
</style>
</head>
<body>
    <br>
    <br>
    <div align="center">
 
  
        <p>Awesome.. Following files are uploaded successfully.</p>
      
        <ol>
            <c:forEach items="${files}" var="file">
           ${file} <img src="${pageContext.request.contextPath}/resources/images/${file}"  alt="Friday Game" /><br> 
            </c:forEach>
        </ol>
        <a href="http://localhost:8080/Friday_game_project/ImageTest.html"><input
            type="button" value="Go Back" /></a> <br />
        <br />
        <br />
        
    </div>
</body>
</html>