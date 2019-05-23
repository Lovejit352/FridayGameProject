<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
        <%@include file="includeStyle.jsp" %>
    <title>Game Hub Dashboard</title>
  </head>
  <body class="bgTrainee">
<jsp:include page="includeNavbar.jsp"></jsp:include>
  


<!--======Trainne======-->
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
                        <a class="btn btn-warning btn-block col-sm-6 mx-auto" href= "${pageContext.request.contextPath }/pastgames/0/${lastGame.gameId}">View Game</a>
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
                        <a class="btn btn-warning btn-block col-sm-6 mx-auto" data-toggle="modal" data-target="#joinTeamForm">Join Game</a>
                </div>
            <p>&nbsp;</p>
            </div>
        </div>
        </c:if>        
        
    </div>
</div>
<!--======Trainnee END======-->
<!--======Team MODAL======-->
<div class="modal fade" id="joinTeamForm">
    <div class="modal-dialog modal-xl p-4">
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
                                <form action="addTeamMember" method="post">
                            	    <input type="hidden" name="gameId" value="${thisGame.gameId }">
                            	    <input type="hidden" name="teamId" value="${team.teamId}">
                            	    <button type="submit" class="btn btn-warning">Join</button>
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

      
<%@include file="includeBoostrapBottom.jsp" %>
</body>
</html>