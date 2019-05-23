<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<%@include file="includeStyle.jsp" %>
<title>Friday Game Hub</title>
</head>
<body class="bgIndex m-0 p-0">
<%@include file="includeLoginRegForms.jsp" %>

<!--======FGM HERO======-->
    <div class="container-fluid">
        <div class="titleHeading w-100 text-center">
            <h1 data-aos="fade-right" class="neonDiv pl-2 w-100 mx-auto rounded-pill hFont1 text-center">FRIDAY GAME HUB</h1>
        </div>
    </div>
<!--======FGM HERO END======-->
<!--======Voting list======-->
<c:if test="${submittedGames.size() > 0 }">
<div class="container-fluid">
<div class="mx-auto my-5 p-0 neonDivNoAni">
    <div class="aosaos " data-aos="flip-up">
        <div class="text-center"><p class="neonText"><span>V</span>oting <span>L</span>ist</p></div>
    </div>
    <div class="album py-5 bg-transparent">
        <div class="container">
            <div class="row">
                <c:forEach items="${submittedGames }" var="game">
				    <div class="col-xl-4 p-4">
	                    <div class="neonDivNoAni text-center bgBrick">
	                       <div class="text-center mx-auto my-4" data-toggle="modal" data-target="#game${gameId}">
	                           <img width="300" height="300" class="p-0 m-0 rounded" src="game1.jpg" />
	                        </div>
	
	                        <div class="container mx-auto">
	                            <p class="neonTextLogo text-light">${game.gameName}</p>
	                            <a class="btn btn-primary btn-block " href="#"  data-toggle="modal" data-target="#loginDiv">Vote</a>
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
</div>
<!--======Voting list======-->
<!--======GAME INFO MODAL======-->
<c:forEach items="${submittedGames }" var="game">
<div>
<div class="modal fade" id="game${gameId}" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog modal-xl" role="document">
    <div class="modal-content">
    <div class="neonDiv bgBrick p-4 text-light">
        <button type="button text-light" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <div class="row">
            <div class="col-md-4 h-100">
                <img class="img-fluid rounded pt-4 pl-2" src="game1.jpg" class="img w-100 h-100" alt="...">
            </div>
            <div class="col-md-8">
                <div class="text-center mx-auto "><p class="neonText">Ga<span>me</span> Name</p></div>
                <div class="container-fluid"> 
                    <p class="lead">${game.gameDesc}
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
</div>
</c:forEach>
<!--======GAME INFO MODAL END======-->
</c:if>


<!--======Past Games ======-->
<div class="container-fluid ">
<div class="mx-auto my-5 p-0 neonDivNoAni">
    <div class="aosaos mx-auto " data-aos="flip-up">
        <div class="text-center mx-auto "><p class="neonText"><span>P</span>ast <span>G</span>ames</p></div>
    </div>
    <div class="album py-5 bg-transparent  ">
        <div class="container">
            <div class="row">
                
                <c:forEach items="${pastGames}" var="game">
              	<div class="col-xl-4 p-4">
                    <div class="neonDivNoAni text-center">
                       <div class="text-center mx-auto my-4">
                           <img width="300" height="300" class="p-0 m-0 rounded" src="resources/images/${game.gameId}" />
                        </div>

                        <div class="container mx-auto">
                            <p class="neonTextLogo text-light">${game.gameName}</p>
                            <a class="btn btn-primary btn-block " href="#" data-toggle="modal" data-target="#loginDiv">Like</a>
                        </div>
                        <!--added to prevent overflow-->
                        <p class="p-2">&nbsp;</p>
                    </div>
                </div>
				</c:forEach>
                
            </div>
            <div class="row">
                <a href="pastgames/0" class="btn btn-warning btn-block"> Show More</a>
            </div>
        </div>
    </div>
</div>
</div>
<%@include file="includeBoostrapBottom.jsp" %>
</body>
</html>