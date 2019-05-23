<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.fdmgroup.model.User, java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<%@include file="includeStyle.jsp" %>
	<script src="resources/js/AdminControl.js"></script>
	<title>Game Trainee dashboard</title>
</head>
<body class="bgTrainee">
<!--======NAV BAR=======-->
<%@include file="includeNavbar.jsp" %>

<!--navbar ends-->
<!--  current game -->
<!--======Trainne======-->
<div class="container-fluid">
    <div class="p-5 m-5 neonDivNoAni text-light bgBrick text-center userList">
        <p class="neonText">User List</p>
        <div class="p-2">
            <form class="row" action="searchusers" method="POST">
                <input name="searchName" class=" col-lg-3 form-control m-2" placeholder="Full name"/>
                <input name="searchEmail" class=" col-lg-3 form-control m-2 " placeholder="email address"/>
                <input type="submit" class=" col-lg-2 form-control m-2 btn-warning" value="Search"/>
            </form>
        </div>
        <div class="row my-2 py-2 bg-warning text-dark">
            <div class="col-lg-4">
                <p>Full Name</p>
            </div>
            <div class="col-lg-4">
                <p>Email Address</p>
            </div>
            <div class="col-lg-2">
                <p>UserType</p>
            </div>
            <div class="col-lg-2">
            </div>
        </div>
        <%				
			for (User user : (List<User>)request.getAttribute("users")) {
		%>
        <div class="row py-0 my-1 border border-light py-1">
            <div class="col-lg-4">
                <p><%= user.getFullName() %></p>
            </div>
            <div class="col-lg-4">
                <p><%= user.getEmailAddress() %></p>
            </div>
            <div class="col-lg-2">
                <p><%= user.getUserType().toString() %></p>
            </div>
            <div class="col-lg-2">
                <a class="btn btn-warning btn-block mx-auto w-50 m-0 p-0 rounded-pill" data-toggle="modal" data-target="#edit100001" 
                		onClick="editUser('<%= user.getFullName() %>', '<%= user.getEmailAddress() %>',
                							'<%= user.getUserType().toString() %>', '<%= user.getUserId() %>')" >edit</a>
            </div>
        </div>
        <% 
        	} 
        %>
        <div class="row">
            <div class="">
                <nav >
                  <ul class="pagination justify-content-center ">

					<%				
						for (int i = 0; i < (Integer)request.getAttribute("pages"); i++) {
					%>
		
                    <li class="page-item">
                    	<a class="page-link" href="${pageContext.request.contextPath}/dash-admin-<%= i %>">
                    		<%= i + 1 %>
                    	</a>
                    </li>

					<%
						}	
					%>

                  </ul>
                </nav>                   
            </div>
        </div>

        
    </div>
</div>
<!--======Trainnee END======-->

<!-- current game -->
<div class="container-fluid mx-auto my-5 p-0 ">
    <div class="row">
        <div class="col-lg-1"></div>
        <c:if test="${not empty lastGame }">
        <div class="col-lg-5">
            <div class="neonDivNoAni mx-auto text-center bgBrick p-4">
                <p class="neonText">Last Friday Game</p>
                <div class="">
                    <img width="500" height="500" class="rounded" src="resources/images/${lastGame.gameId}" />
                </div>
                <div class="p-4">
                        <p class="neonTextLogo">${lastGame.gameName}</p>
                        <a class="btn btn-warning btn-block col-sm-6 mx-auto" href="${pageContext.request.contextPath }/pastgames/0/${lastGame.gameId}">View Game</a>
                </div>
            <p>&nbsp;</p>
            </div>
        </div>
        </c:if>
        <c:if test="${not empty thisGame }">
        <div class="col-lg-5">
            <div class="neonDiv mx-auto text-center bgBrick p-4">
                <p class="neonText">This Friday Game</p>
                <div class="">
                    <img width="500" height="500" class="rounded" src="resources/images/${thisGame.gameId}" />
                </div>
                <div class="p-4">
                        <p class="neonTextLogo">${thisGame.gameName}</p>
                        <a class="btn btn-warning btn-block col-sm-6 mx-auto" data-toggle="modal" data-target="#closeGame">Close Game</a>
                </div>
            <p>&nbsp;</p>
            </div>
        </div>
        </c:if>        
        
    </div>
</div>
<!-- current game ends -->


<!--======Team MODAL======-->
<div class="modal fade" id="closeGame">
    <div class="modal-dialog modal-xl  p-4">
        <div class="modal-content neonDiv bgBrick ">
        <div class="text-light p-4">
        <!-- Modal body -->
            <div>
               
                <div class="row">
                <c:forEach items="${teams}" var="team">
                    <div class="col-lg-3 mx-auto mt-2">
                        <div class="row p-1 border border-warning m-1 rounded p-1 pt-2">
                            <div class="col-md-6">
                                <p class="neonTextLogo">Team:</p>
                            </div>
                            <div class="col-md-6">
                                <form action="${pageContext.request.contextPath }/closeGame" method="post">
                            	    <input type="hidden" name="gameId" value="${thisGame.gameId }">
                            	    <input type="hidden" name="teamId" value="${team.teamId}">
                            	    <button type="submit" class="btn btn-warning btn-sm">Winner</button>
                                </form>
                            </div>
                            <div class="col-md-12">
                            <c:forEach items="${team.teamMembers}" var="memeber">
                              <p class="text-light p-0 m-0 pb-1 neonTextUsername ">${memeber.fullName } </p>
                              </c:forEach>
                            </div>
                        </div>
                    </div>
                  </c:forEach>  
                  
                    
                </div>
            </div>
            
            
        </div>
        </div>
    </div>
</div>
<!--======Team MODAL END======-->



<!--======Team MODAL======-->
<div class="modal fade" id="edit100001">
    <div class="modal-dialog modal-lg  p-4">
        <div class="modal-content">
        <div class="bgBrick text-light p-5 neonDiv">
            <p class="neonText">Edit User:</p>
            <form class="" action="adminedituser-<%= request.getAttribute("page_id") %>" method="POST">
                  <div class="form-group row">
                    <label for="fullName" class="col-sm-2 col-form-label">Full name: </label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control" id="fullName" value="Full name" name="fullname">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label for="emailAddress" class="col-sm-2 col-form-label">Email: </label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control" id="emailAddress" value="email@example.com" name="email">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label for="userType" class="col-sm-2 col-form-label">UserType: </label>
                    <div class="col-sm-10">
                      <select id="userTypes" class="form-control" name="usertype">
                          <option>Admin</option>
                          <option>GameManager</option>
                          <option>Trainee</option>
                       </select>
                    </div>
                  </div>
                  <input style="visibility: hidden; display:none" type="text" id="edituserid" name="edituserid">
                <input type="submit" value="submit" class="btn btn-warning"/>
            </form>
            
        </div>
        </div>
    </div>
</div>
<!--======Team MODAL END======-->

<%@include file="includeBoostrapBottom.jsp" %>

</body>
</html>