<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.fdmgroup.model.Game, java.util.List" %>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
      <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

    <title>Friday game manager</title>
  </head>
  <body>

<!--loginShowBtn-->
<div class="showLoginBtn position-fixed sticky bg-dark rounded-circle">
        <button id="showBtn" type="button" class="btn btn-warning" data-toggle="modal" data-target="#loginDiv">LOGIN</button>
</div>

<div id="loginDiv"class="modal fade text-white" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
<!--        loginRegistrationForms-->
        <div class="loginRegisterForms ">
    
<!--  loginForm -->
<div class="loginForm bg-dark text-light mx-auto active neonDiv p-2 w-75">
      
    <button type="button" class="py-3 close" data-dismiss="modal" aria-label="Close">
      <span aria-hidden="true">&times;</span>
    </button>
    <img class="mx-auto img-fluid rounded-circle" src="resources/images/FridayGame.png"/>
    <form class="m-3 p-3 text-center">
      <h1 class="h3 mb-3 font-weight-normal py-2 ">LOGIN</h1>
      <label for="inputEmail" class="sr-only">Email address</label>
      <input type="email" id="inputEmail" class="my-2 px-4 form-control" placeholder="Email address" required autofocus>
      <label for="inputPassword" class=" sr-only">Password</label>
      <input type="password" id="inputPassword" class="my-2 px-4 form-control" placeholder="Password" required>
    </form>
      <div class="row p-2 mx-2">
        <div class="col-sm-6">
            <button class="btn btn-lg btn-danger btn-block text-white font-weight-bold " type="submit">Sign in</button>
          </div>
        <div class="col-sm-6">
            <button id="registerBtn" class="btn btn-lg btn-outline-warning btn-block text-white font-weight-bold" >Register</button>
          </div>
        </div>
  </div>

<!--  RegisterForm -->
<div class="registerForm bg-dark text-light mx-auto neonDiv p-2 w-75">
      
    <button type="button" class="py-3 close" data-dismiss="modal" aria-label="Close">
      <span aria-hidden="true">&times;</span>
    </button>
    <img class="mx-auto img-fluid rounded-circle" src="resources/images/FridayGame.png"/>
    <form class="m-3 p-3 text-center">
      <h1 class="h3 mb-3 font-weight-normal py-2 ">REGISTER</h1>
      <label for="inputEmail" class="sr-only">Email address</label>
      <input type="email" id="inputEmail" class="my-2 px-4 form-control" placeholder="Email address" required autofocus>
      <label for="inputFullName" class="sr-only">Full Name</label>
      <input type="text" id="inputFullName" class="my-2 px-4 form-control" placeholder="Full Name" required >
      <label for="inputPassword" class=" sr-only">Password</label>
      <input type="password" id="inputPassword" class="my-2 px-4 form-control" placeholder="Password" required>
    </form>
      <div class="row p-2 mx-2">
        <div class="col-sm-6">
                <button id="loginBtn" class="btn btn-lg btn-outline-warning  btn-block text-white font-weight-bold" type="submit">Sign in</button>
        </div>
          <div class="col-sm-6">
            <button class="btn btn-lg btn-danger btn-block btn-danger text-white font-weight-bold" type="submit">Register</button>
          </div>
    </div>


</div>
    </div>
    </div>
  </div>
</div> 

<div class="container-fluid m-auto">
    <div class="row">
        <div class="col-lg-2 m-0 p-0 px-2 bg-dark text-white ">
        
            <div class="menu sticky-top m-0 py-4 rounded">
                <h5 class="">Game List</h5>
                <div class="nav flex-column text-wrap justify-content-center m-0 p-0">
                
                
                	<%
					if((List<Game>)request.getAttribute("games") != null) {
					
						for (Game game : (List<Game>)request.getAttribute("games")) {
					%>
                	
                
                    <a href="${pageContext.request.contextPath}/pastGames/<%= game.getGameId() %>/${page}" class="btn p-0 m-1 shadow nav-link rounded-0 font-weight-bold btn-danger text-dark">
                        <div class="m-0 p-0 mt-3">
                            <p class=" "><%= game.getGameName() %>
                            <span class="badge px-2">
                                <img class="img-responsive" height="20" width="20" src="grin-hearts-solid.svg"/>
                                <%= game.getVotes() %>
                            </span>
                            </p>
                        </div>
                        <div class=" m-0 p-0">
                                <p class=""> <%= game.getPlayedOn() %> </p>
                        </div>
                    </a>
                                      
                    
                    <%				
						}
					}
					%>
                    
                    
                    <!--  -->
                    
                    
                                        <div class="mt-4">
                        <nav aria-label="Page navigation example " >
                          <ul class="pagination justify-content-center ">
							<%				
								for (int i = 0; i < (Integer)request.getAttribute("pages"); i++) {
							%>
                            <li class="page-item">
                            	<a class="page-link" href="${pageContext.request.contextPath}/pastgames<%= i %>">
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
        
            
        <div class="col-lg-10 m-0 p-0 pl-4">
            <div >
                
                
                 <div class="gameBg">
                  <img class="img-responsive img-fluid w-100" src="GameDetails.png" />
                  <div class="centered">
                    <p class="text-dark">${ pastGame.getGameName() }</p>
                  </div>
                </div>
                
                
                <div class="py-4">
                    <h1>Game Description</h1>
                    <p>${ pastGame.getGameDesc() } </p>
                </div>
                <div class="shadow my-3 py-3">
                    <h3 class="px-4">Helpers:</h3>
                    <div class="d-flex flex-row ml-3">
                    <c:forEach items="${ pastGame.getHelpers() }" var="helper">
                    <a href="#" class="p-2 ">${helper.getFullName()}</a>
                    </c:forEach>
                       </div>
                   
                </div>
                <div class="shadow my-3 py-3">
                    <h3 class="px-4">Winners:</h3>
                    <div class="d-flex flex-row ml-3">
                      <a href="#" class="p-2 ">Full name</a>
                      <a href="#" class="p-2 ">Full name</a>
                      <a href="#" class="p-2 ">Full name</a>
                    </div>
                </div>
                <div class="shadow my-3 py-3">
                    <h3 class="px-4">Teams:</h3>
                    <c:forEach items="${ pastGame.getTeams() }" var="team">
                    <div class="d-flex flex-row ml-3">
                     <p href="#" class="p-2 ">Teams ${team.getTeamId()}:</p>
                    <c:forEach items="${team.getTeamMembers() }" var="teamMember">
                      <a href="#" class="p-2 ">${teamMember.getFullName() }</a>
                    </c:forEach>
                     </div>
                    </c:forEach>
                                          
                </div>
                <div class="py-4">
                    <h1>Comments</h1>
                    <c:forEach items="${comments }" var="comment">
		            	<p class="shadow p-3 rounded-pill w-75">${comment.getComment()}</p>        	
                    </c:forEach>
                    
                
                
                </div>
                
            </div>
        </div>
    
    </div>


</div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
      <script type="text/javascript" src="resources/js/FridayGame.js"></script>
    </body>
</html>