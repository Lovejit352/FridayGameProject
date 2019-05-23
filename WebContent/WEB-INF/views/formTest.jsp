<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@page import="java.util.*"%>
    <%@ page import="com.fdmgroup.model.*"%>
    <%@page import="java.awt.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
*{
padding:0.2rem;
margin:0.2rem;
text-align: left;
}
form{
float:left;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<p style="color:blue;">Logged in as: ${sessionUser.fullName}</p>
<p style="color:red;">${errorMessage }</p>
<p style="color:green;">${successMessage}</p>
<p >${result}</p>

<form method="post" autocomplete="off"  action="${pageContext.request.contextPath}/login">
 <fieldset>
  <legend>/login:</legend>
  emailAddress: <input name="emailAddress" type="text"><br>
  password: <input name="password" type="text"><br>
  <input type="submit" value="submit"/>
 </fieldset>
</form>

<form method="post" autocomplete="off"  action="${pageContext.request.contextPath}/usercreate">
 <fieldset>
  <legend>"/usercreate"</legend>
  fullname: <input name="fullname" type="text"><br>
  password: <input name="password" type="text"><br>
  emailAddress: <input name="emailAddress" type="text"><br>
  userType: <input name="userType" type="text"><br>
  <input type="submit" value="submit"/>
 </fieldset>
</form>

<form method="post" autocomplete="off"  action="${pageContext.request.contextPath}/userupdate">
 <fieldset>
  <legend>"/userupdate"</legend>
  fullname: <input name="fullname" type="text"><br>
  password: <input name="password" type="text"><br>
  emailAddress: <input name="emailAddress" type="text"><br>
  userType: <input name="userType" type="text"><br>
  <input type="submit" value="submit"/>
 </fieldset>
</form>

<form method="post" autocomplete="off"  action="${pageContext.request.contextPath}/changepassword">
 <fieldset>
  <legend>"/changepassword"</legend>
  currentPassword: <input name="currentPassword" type="text"><br>
  newPassword: <input name="newPassword" type="text"><br>
  confirmNewPassword: <input name="confirmNewPassword" type="text"><br>
  <input type="submit" value="submit"/>
 </fieldset>
</form>

<form method="post" autocomplete="off"  action="${pageContext.request.contextPath}/getUserById">
 <fieldset>
  <legend>"/getUserById"</legend>
  userId: <input name="userId" type="text"><br>
  <input type="submit" value="submit"/>
 </fieldset>
</form>

<form method="post" autocomplete="off"  action="${pageContext.request.contextPath}/users0">
 <fieldset>
  <legend>"/getUserById"</legend>
  <input type="submit" value="submit"/>
 </fieldset>
</form>

<form method="post" autocomplete="off"  action="${pageContext.request.contextPath}/getUserByEmailAddress">
 <fieldset>
  <legend>"/getUserById"</legend>
  emailAddress: <input name="emailAddress" type="text"><br>
  <input type="submit" value="submit"/>
 </fieldset>
</form>

<form method="post" autocomplete="off"  action="${pageContext.request.contextPath}/getAllTeamsByGameId">
 <fieldset>
  <legend>"/getAllTeamsByGameId"</legend>
  gameId: <input name="gameId" type="text"><br>
  <input type="submit" value="submit"/>
 </fieldset>
</form>

<form method="post" autocomplete="off"  action="${pageContext.request.contextPath}/addTeamMember">
 <fieldset>
  <legend>"/addTeamMember"</legend>
  gameId: <input name="gameId" type="text"><br>
  userId: <input name="userId" type="text"><br>
  addToTeamId: <input name="addToTeamId" type="text"><br>
  random: <input name="random" type="text"><br>
  <input type="submit" value="submit"/>
 </fieldset>
</form>

<form method="post" autocomplete="off"  action="${pageContext.request.contextPath}/createGame">
 <fieldset>
  <legend>"/createGame"</legend>
  gameName: <input name="gameName" type="text"><br>
  gameDesc: <input name="gameDesc" type="text"><br>
  numOfTeams: <input name="numOfTeams" type="text"><br>
  playersPerTeam: <input name="playersPerTeam" type="text"><br>
  <input type="submit" value="submit"/>
 </fieldset>
</form>

<form method="post" autocomplete="off"  action="${pageContext.request.contextPath}/updateGame">
 <fieldset>
  <legend>"/updateGame"</legend>
  gameId: <input name="gameId" type="text"><br>
  gameName: <input name="gameName" type="text"><br>
  gameDesc: <input name="gameDesc" type="text"><br>
  numOfTeams: <input name="numOfTeams" type="text"><br>
  playersPerTeam: <input name="playersPerTeam" type="text"><br>
  <input type="submit" value="submit"/>
 </fieldset>
</form>

<form method="post" autocomplete="off"  action="${pageContext.request.contextPath}/getGameById">
 <fieldset>
  <legend>"/getGameById"</legend>
  gameId: <input name="gameId" type="text"><br>
  <input type="submit" value="submit"/>
 </fieldset>
</form>

<form method="post" autocomplete="off"  action="${pageContext.request.contextPath}/getGameFetch">
 <fieldset>
  <legend>"/getGameFetch"</legend>
  gameId: <input name="gameId" type="text"><br>
  <input type="submit" value="submit"/>
 </fieldset>
</form>

<form method="post" autocomplete="off"  action="${pageContext.request.contextPath}/addHelper">
 <fieldset>
  <legend>"/addHelper"</legend>
  gameId: <input name="gameId" type="text"><br>
  userId: <input name="userId" type="text"><br>
  <input type="submit" value="submit"/>
 </fieldset>
</form>

<form method="post" autocomplete="off"  action="${pageContext.request.contextPath}/votecheck">
<fieldset>
  <legend>"/votecheck"</legend>
  <input type="submit" value="submit"/>
</fieldset>
</form>

<form method="post" autocomplete="off"  action="${pageContext.request.contextPath}/createComment">
 <fieldset>
  <legend>"/createComment"</legend>
  userId: <input name="userId" type="text"><br />
  gameId: <input name="gameId" type="text"><br>
  parentCommentId: <input name="parentCommentId" type="text"><br>
  strComment: <input name="strComment" type="text"><br>
  <input type="submit" value="submit"/>
 </fieldset>
</form>

<form method="post" autocomplete="off"  action="${pageContext.request.contextPath}/updateComment">
 <fieldset>
  <legend>"/updateComment"</legend>
  commentId: <input name="commentId" type="text"><br>
  strComment: <input name="strComment" type="text"><br>
  <input type="submit" value="submit"/>
 </fieldset>
</form>

<form method="post" autocomplete="off"  action="${pageContext.request.contextPath}/getCommentById" style="clear:left">
 <fieldset>
  <legend>"/getCommentById"</legend>
  commentId: <input name="commentId" type="text" ><br>
  <input type="submit" value="submit"/>
 </fieldset>
</form>

<form method="post" autocomplete="off"  action="${pageContext.request.contextPath}/getAllCommentsByGameId">
 <fieldset>
  <legend>"/getAllCommentsByGameId"</legend>
  gameId: <input name="gameId" type="text"><br>
  <input type="submit" value="submit"/>
 </fieldset>
</form>


<p style="clear:both;">getCommentById: ${comment.getComment()}</p>

<div class="container">
<table class="accountinfo modal-content animate">
<caption class="caption">Get Game By Id</caption>
<tr>
  <th>Game Id</th>
  <th>Comment</th>
</tr>
<c:forEach items="${comments}" var="comment">
  <tr>
    <td>${comment.game.getGameId() }</td>
    <td>${comment.getComment() }</td>
  </tr>
    
</c:forEach>
</table>
</div>

</body>
</html>