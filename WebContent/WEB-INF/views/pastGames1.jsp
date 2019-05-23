<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.fdmgroup.model.UserType"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="includeStyle.jsp"></jsp:include>
<title>Past Games</title>
</head>
<body class="bgGameMaker">
<c:choose>
<c:when test="${sessionUser == null }">
<%@include file="includeLoginRegForms.jsp" %>
</c:when>
<c:otherwise>
<%@include file="includeNavbar.jsp" %>
</c:otherwise>
</c:choose>



<div class="container-fluid m-auto">
    <div class="row">
        <div class="col-lg-2 m-0 p-0 px-2 text-white mt-2">
            <div class="menu sticky-top m-0 py-4 mt-2">
            
            <c:choose>
				<c:when test="${sessionUser == null}">
            		<p class="neonText m-0 p-0"><a href="${pageContext.request.contextPath }/">Home</a></p>
            	</c:when>
            </c:choose>
                    	
                <div class="nav flex-column text-wrap justify-content-center m-0 p-0">
                    
                    
                   <c:forEach items="${games}" var="game">
                    <a href="${pageContext.request.contextPath }/pastgames/${page}/${game.gameId}" 
                    class="btn p-0 m-1  nav-link font-weight-bold bg-warning rounded">
                        <div class="m-0 p-0 mt-1">
                            <p class="p-0 m-2">${game.gameName }
                            <span class="badge px-2 p-0">
                                <i class="fas fa-thumbs-up"></i>
                                ${game.rating }
                            </span>
                            </p>
                        </div>
                        <div class=" m-0 p-0">
                                <p class=" m-2 p-0">Played on : <fmt:formatDate type = "date"  value="${game.getPlayedOn() }" /></p>
                        </div>
                        <div class=" m-0 p-0">
                                <p class=" m-2 p-0">Created By: ${game.createdBy.fullName }</p>
                        </div>
                        
                    </a>
                   </c:forEach> 
                    <div class="mt-4">
                        <nav aria-label="Page navigation example " >
                          <ul class="pagination justify-content-center ">
                          <c:if test="${games.size() > 0 }">
							<c:forEach var="i" begin="0" end="${pages-1}">
								<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath }/pastgames/${i}">${i+1}</a></li>
                            </c:forEach>
                          </c:if>
                            
                          </ul>
                        </nav>                   
                    </div>
                    
                </div>
            </div>
        </div>
        
            
        <div class="col-lg-10 m-0 p-0 pl-4 bgBrick ">
            <div class=" text-light">
                
                
                <div class="gameBg">
                  <img class="img-responsive img-fluid w-100" src="${pageContext.request.contextPath }/resources/images/${pastGame.gameId}" />
                  <div class="centered">
                    <p class="">${pastGame.gameName }</p>

                    
                  </div>
                </div>
                
                
                <div class="py-4">
                    <c:set var="hasLikedGame" value="false" scope="page"/>
					<c:forEach var="user" items="${pastGame.likes }">
                    	<c:choose>
                    		<c:when test="${user.userId == sessionUser.userId }">
			           			<c:set var="hasLikedGame" value="true" scope="page"/>
                    		</c:when>
                    	</c:choose>
                    </c:forEach>
		            <c:set var="isHelper" value="false" scope="page"/>
					<c:forEach var="user" items="${pastGame.helpers }">
                    	<c:choose>
                    		<c:when test="${user.userId == sessionUser.userId }">
			           			<c:set var="isHelper" value="true" scope="page"/>
                    		</c:when>
                    	</c:choose>
                    </c:forEach>
		                    
							
					<form method="post" class="">
                    	    <input type="hidden" name="gameId" value="${ pastGame.gameId}">
                    	     <input type="hidden" name="pageId" value="${page}">
                    	     
							<c:choose>
								<c:when test="${sessionUser == null}">
									<button type="button" class="btn btn-warning w-25"  data-toggle="modal" data-target="#loginDiv"><i class="fas fa-thumbs-up"></i> Like </button>
									
								</c:when>
								<c:when test="${isHelper == true || pastGame.createdBy.userId == sessionUser.userId}">
									<p>== ${hasLikedGame } ==</p>
								</c:when>
								<c:when test="${hasLikedGame == false}">
									<input type="hidden" name="actionType" value="like">
									<button type="submit" class="btn btn-warning float-right mr-2"><i class="fas fa-thumbs-up"></i> Like </button>
									
								</c:when>
								<c:otherwise>
									<input type="hidden" name="actionType" value="unlike">
									<button type="submit" class="btn btn-warning float-right mr-2"><i class="fas fa-thumbs-down"></i> UnLike </button>
										
								</c:otherwise>
							</c:choose>
							                    
                    </form>
					<h1 class="h1 my-2">Game Description</h1>
          			<p>${pastGame.gameDesc}</p>
                   
                </div>
                <div class=" my-3 py-3">
                    <h1 class="px-4">Helpers:</h1>
                    <div class="d-flex flex-row ml-3">
                      <c:forEach var="user" items="${pastGame.helpers }">
	                      	<p href="#" class="p-2 ">${user.fullName }</p>
                      </c:forEach>
                    </div>
                </div>
                <div class=" my-3 py-3">
                    <h1 class="px-4">Winners:</h1>
                    <div class="d-flex flex-row ml-3">
                      <c:forEach var="user" items="${pastGame.winners }">
	                      	<p href="#" class="p-2 ">${user.fullName }</p>
                      </c:forEach>
                    </div>
                </div>
                <div class=" my-3 py-3">
                	<h1 class="px-4">Teams:</h1>
                    <c:forEach var="team" items="${pastGame.teams}" >
	                    <div class="d-flex flex-row ml-3">
	                      <p href="#" class="p-2 ">Team :</p>
	                      <c:forEach var="user" items="${team.teamMembers }">
	                      	<p href="#" class="p-2 ">${user.fullName }</p>
	                      </c:forEach>
	                    </div>
                    </c:forEach>
                    
                </div>
                <div class="py-4">
                    <h1>Comments</h1>
                    <div class="container-fluid mx-auto">
                    	<%-- <form action="${pageContext.request.contextPath }/createComment" method="post"> --%>
                    	
                    	<form method="post">
                    		<input type="hidden" name="actionType" value="createComment">
                    		<textarea class="w-75 mx-auto rounded p-2" name="strComment" rows="5" ></textarea><br/>
	                    	<c:choose>
	                    		<c:when test="${sessionUser !=null }">
			                    		<button class="btn btn-warning m-2" type="submit">Submit</button>
	                    		</c:when>
	                    		<c:otherwise>
	                    				<button type="button" class="btn btn-warning m-2" data-toggle="modal" data-target="#loginDiv">Submit</button>
	                    		</c:otherwise>
	                    	</c:choose>
                    	</form>
                    </div>
                    
                    <c:forEach var="comment" items="${comments}">
                		    <div class=" py-3 m-2 pl-5 rounded-pill w-75">
			            		<%-- <c:choose>
	                    		<c:when test="${comment.parentComment != null }">
		                    	    <div class=" py-3 m-2 pl-5 rounded-pill w-75  ml-5">
			                    </c:when>
	                    		<c:otherwise>
				                    <div class=" py-3 m-2 pl-5 rounded-pill w-75">
			            		</c:otherwise>
	                    		</c:choose>
	                    		 --%>
                    		 <span>${comment.user.fullName } :</span>
		                    	<span><fmt:formatDate value="${comment.getCreatedOn()}" pattern="yyyy-MM-dd" /></span>
			                    	<p class="m-0 p-0" >${comment.comment }</p>
		                    </div>	
                    		
                    </c:forEach>
                   

                    
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="includeBoostrapBottom.jsp"></jsp:include>
</body>
</html>