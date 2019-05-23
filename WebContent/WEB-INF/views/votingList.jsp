<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="includeStyle.jsp" %>
<title>Voting list</title>
</head>
<body class="bgBrick">
<%@include file="includeNavbar.jsp" %>


<!--======Voting list======-->
<div class="container mx-auto my-5 p-0 ">
    <div class="aosaos mx-auto " data-aos="flip-up">
        <div class="text-center mx-auto "><p class="neonText"><span>V</span>oting <span>L</span>ist</p></div>
    </div>
    <div class="album py-5 bg-transparent">
        <div class="container-fluid">
            <div class="row">
                <c:forEach var="game" items="${gameList}">
                <div class="col-xl-4 p-4">
                    <div class="neonDivNoAni text-center">
                       <div class="text-center mx-auto my-4" data-toggle="modal" data-target="#game${game.gameId }">
                           <img width="300" height="300" class="p-0 m-0 rounded" src="resources/images/${game.gameId}" />
                        </div>

                        <div class="container mx-auto">
                            <p class="neonTextLogo text-light">${game.gameName }</p>
                            <div>
                            	<c:choose>
                            		<c:when test="${sessionUser.getGamesvoted() == null || sessionUser.getGamesvoted().size() < 1 }">
		                            	<form method="post">
		                            		<input type="hidden" id="gameId" name="gameId" value="${game.gameId }">
				                            <input type="hidden" id="action" name="action" value="vote">
				                            <button class="btn btn-warning btn-block " type="submit">Vote</button>
		                            	</form>
	                            		
                            		</c:when>
                            		<c:otherwise>
                            			<c:forEach var="votedGame" items="${sessionUser.getGamesvoted()}">
			                            	<c:choose>
			                            		<c:when test="${votedGame.gameId  eq game.gameId }">
				                            		<form method="post">
					                            		<input type="hidden" id="gameId" name="gameId" value="${game.gameId }">
						               				     <input type="hidden" id="action" name="action" value="unvote">
					                                    <button class="btn btn-warning btn-block " type="submit">Un vote</button>
					                            	</form>
			                            		</c:when>
			                            		<c:otherwise>
				                            			<p class="alphaBlack" type="submit">&nbsp;</p>
			                            		</c:otherwise>
			                            	</c:choose>
                            			</c:forEach>
                            		</c:otherwise>
                            	</c:choose>
                            </div>
                        </div>
                        <!--added to prevent overflow-->
                        <p class="p-2">&nbsp;</p>
                    </div>
                </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<!--======Voting list end======-->
<!--======GAME INFO MODAL======-->
<div>
<c:forEach var="game" items="${gameList}">
                
<div class="modal fade" id="game${game.gameId }" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog modal-xl" role="document">
    <div class="modal-content">
    <div class="neonDiv bgBrick p-4 text-light">
        <button type="button text-light" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <div class="row">
            <div class="col-md-4 h-100">
                <img class="img-fluid rounded pt-4 pl-2" src="game1.jpg" class="img w-100 h-100" >
            </div>
            <div class="col-md-8">
                <div class="text-center mx-auto "><p class="neonText">${game.gameName}</p></div>
                <div class="container-fluid"> 
                    <p class="lead">
                    ${game.gameDesc }
                     </p>
                 </div>
              </div>
        </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Vote</button>
      </div>
    </div>
    </div>
  </div>
</div>

</c:forEach>
</div>
<!--======CREATE GAME MODAL END======-->


<%@include file="includeBoostrapBottom.jsp" %>
</body>
</html>