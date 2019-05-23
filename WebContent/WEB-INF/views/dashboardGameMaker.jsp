<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="includeStyle.jsp" %>
<title>Game Maker dashboard</title>
</head>
<body class="bgGameMaker">
<jsp:include page="includeNavbar.jsp"></jsp:include>


<!--======CREATE GAME======-->
<div class="container mx-auto my-5 p-0 ">
    <div class="aosaos mx-auto " data-aos="flip-up">
        <div class="text-center mx-auto "><p class="neonText"><span>C</span>rea<span>t</span>e Ga<span>me</span></p></div>
    </div>
    <div class="album py-5 bg-transparent">
        <div class="container-fluid">
            <div class="row">
            	<c:forEach items="${gameList}" var="game">
                	<div class="col-xl-4">
                    <div class="neonDivNoAni text-center">
                       <div class="text-center mx-auto my-4" >
                           <img width="300" height="300" class="p-0 m-0 rounded" src="resources/images/${game.gameId}" />
                        </div>
						    <div class="container mx-auto">
	                            <p class="neonText text-light">${game.gameName }</p>
	                    	<c:if test="${game.gameStatus eq 'CREATED' }">
	                            <a class="btn btn-primary btn-block " href="#" data-toggle="modal" data-target="#edit${game.gameId }">EDIT</a>
	                    	</c:if>
                            </div>
						<!--added to prevent overflow-->
                        <p class="p-2">&nbsp;</p>
                    </div>
                </div>
            	</c:forEach>
            	<c:if test="${gameList.size() <=3 }">
	            	<c:forEach var="i" begin="1" end="${3-gameList.size() }">
	               
	                <div class="col-xl-4">
	                    <div class="neonDivNoAni text-center ">
	                       <div class="text-center mx-auto my-5" >
	                           <p class="neonText mt-plus display-4 my-5">+</p>
	                        </div>
	                       
	                        <div class="container mx-auto">
	                            <p class="h4 neonText text-light">Game Name</p>
	                            <a class="btn btn-primary btn-block " href="#" data-toggle="modal" data-target="#createGame">Add</a>
	                        </div>
	                        <!--added to prevent overflow-->
	                        <p class="p-2">&nbsp;</p>
	                    </div>
	                </div>
	                	
	            	</c:forEach>
				</c:if>
            	

            </div>
        </div>
    </div>
</div>
<!--======CERATE GAME END======-->

<!--====== Edit MODAL ======-->
<c:forEach var="game" items="${gameList}">
<c:if test="${game.gameStatus eq 'CREATED' }">
<div class="modal fade" id="edit${game.gameId }" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content neonDiv bgBrick text-light p-4">
      <form method="post">
      		<input type="hidden" id="gameId" name="gameId" value="${game.gameId }">
            <p class="neonText">Edit Game</p>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	        <div class="row">
		  		<div class="col-lg-3">
					<div class="form-group">
					    <label for="gameName">Game name:</label>
					    <input type="text" class="form-control" name="gameName" id="gameName" value="${game.gameName }">
				  	</div>
		  		</div>
		  		<!-- <div class="col-lg-3">
			  	  <div class="form-group">
				    <label for="teamFormation">Team formation :</label>
				    <select class="form-control" id="teamFormation">
				      
				      <option value="0" selected>Manual</option>
				      <option value="1">Auto</option>
				    </select>
				  </div>		  	
		  		</div> -->
		  		<div class="col-lg-3">
			  	  <div class="form-group">
				    <label for="numOfTeams">Number of teams :</label>
					<input class="w-75 text-center" type="number" id="numOfTeams" name="numOfTeams" min="0" max="20" value="${game.numOfTeams }" required>
				  </div>		  	
		  		</div>
		  		<div class="col-lg-3">
			  	  <div class="form-group">
				    <label for="playersPerTeam ">Players per team :</label>
					<input class="w-75 text-center" type="number" id="playersPerTeam" name="playersPerTeam" min="0" max="20" value="${game.playersPerTeam }" required>
				  </div>		  	
		  		</div>
		  	</div>
	       <div>
	       		<label for="helperList">Current Helpers :</label>
	       		<div id="helperList">
	       			<c:forEach var="user" items="${game.helpers }">
		       			<span>${user.fullName}</span>
	       			</c:forEach>
	       		</div>
	       </div>
	       <div class="row">
		  		<div class="col-lg-3">
			  	  <div class="form-group">
				    <label for="removeHelper">Remove helper :</label>
				    <select class="form-control" id="removeHelper" name="removeHelper">
				      <option value="-1" selected>Select</option>
				      <c:forEach var="user" items="${game.helpers }">
					      <option value="${user.userId }">${user.fullName}</option>
				      </c:forEach>
				    </select>
				  </div>		  	
		  		</div>
		  		<div class="col-lg-3">
			  	  <div class="form-group">
				    <label for="addHelper">Add helper :</label>
				    <select class="form-control" id="addHelper" name="addHelper">
				      <option value="-1" selected>Select</option>
				      <c:forEach var="user" items="${userList }">
					      <option value="${user.userId }">${user.fullName}</option>
				      </c:forEach>
				    </select>
				  </div>		  	
		  		</div>
		  	</div>
	        <div class="form-group">
			    <label for="gameDesc">Game Description:</label>
			    <textarea  class="form-control" name="gameDesc" id="gameDesc" placeholder="Game Description" rows="8" >${game.gameDesc }</textarea>
		  	</div>
		  	<div class="w-100 mx-auto text-center">
		        <button type="submit" class="btn btn-warning mb-4 mx-auto">Save changes</button>
		  	</div>
    	</form>
		  <div class="modal-footer">
		  <div class="row w-100 m-0 p-0">
	    	<form method="post" class="col-lg-6">
		        	<input type="hidden" id="gameId" name="gameId" value="${game.gameId }">
		            <input type="hidden" id="actionType" name="actionType" value="delete">
		            <button type="submit" class="btn btn-danger float-left">DELETE</button>
		  	</form>
	    	<form method="post" class="col-lg-6">
		        	<input type="hidden" id="gameId" name="gameId" value="${game.gameId }">
		            <input type="hidden" id="actionType" name="actionType" value="submit">
		            <button type="submit" class="btn btn-warning float-right">Submit For Voting</button>
		  	</form>
		  </div>
<!-- 	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
 -->	      </div>
    </div>
  </div>
</div>
</c:if>
</c:forEach>
<!--====== Edit MODAL END======-->


<!--====== Create MODAL ======-->
<div class="modal fade" id="createGame" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content neonDiv bgBrick text-light p-4">
      <form method="post" action="createGame" enctype="multipart/form-data">
      		<p class="neonText">Create Game</p>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	        <div class="row">
		  		<div class="col-lg-3">
					<div class="form-group">
					    <label for="gameName">Game name:</label>
					    <input type="text" class="form-control" name="gameName" id="gameName">
				  	</div>
		  		</div>
		  		<!-- <div class="col-lg-3">
			  	  <div class="form-group">
				    <label for="teamFormation">Team formation :</label>
				    <select class="form-control" id="teamFormation">
				      
				      <option value="0" selected>Manual</option>
				      <option value="1">Auto</option>
				    </select>
				  </div>		  	
		  		</div> -->
		  		<div class="col-lg-3">
			  	  <div class="form-group">
				    <label for="numOfTeams">Number of teams:</label>
					<input class="w-75 text-center" type="number" id="numOfTeams" name="numOfTeams" min="0" max="20" required">
				  </div>		  	
		  		</div>
		  		<div class="col-lg-3">
			  	  <div class="form-group">
				    <label for="playersPerTeam ">Players per team :</label>
					<input class="w-75 text-center" type="number" id="playersPerTeam" name="playersPerTeam" min="0" max="20" required">
				  </div>		  	
		  		</div>
		  		
		  		
		  		<div class="col-lg-3">
			  	  <div class="form-group">
				    <label for="gamePicture ">Game Picture :</label>
					<input class="w-75 text-center" type="file" id="gamePicture" name="gamePicture" min="0" max="20" title="" required">
				  </div>		  	
		  		</div>
		  		
		  		
		  	</div>
			<div class="form-group">
			    <label for="gameDesc">Game Description:</label>
			    <textarea  class="form-control" name="gameDesc" id="gameDesc" placeholder="Game Description" rows="8" ></textarea>
		  	</div>
		  <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">Save changes</button>
	      </div>
    	</form>
    </div>
  </div>
</div>
<!--====== Create MODAL END======-->



<!--======CREATE GAME MODAL======-->
<div class="modal fade" id="myModal">
    <div class="modal-dialog modal-lg neonDiv">
        <div class="modal-content bgBrick">
        <!-- Modal body -->
            <div class="containerPop">
            <div class="tabs">
                <div class="tab-2">
                    <label for="tab2-1">Description</label>
                    <input id="tab2-1" name="tabs-two" type="radio" checked="checked">                    
                    <div>
                        <form>
                            <div class=" heading">
                                <input type="text" placeholder="Game Name Here">
                            </div>
                            <div class="textbox">
                                <textarea placeholder="Decription/Rules" rows="10" cols="70"></textarea>
                            </div>
                            <div class="heading">
                                <input type="text" placeholder="Prize">
                            </div>
                            <div class="heading1">
                                <input type="checkbox">Team Formation
                                <select>
                                    <option value="John Doe">John Doe</option>
                                </select>
                            </div>
                            <div class="heading2">
                                <div class="headDiv1">
                                    <input type="number">Number Of Teams<br>
                                    <input type="number">Members Per Team
                                </div>
                                <div class="headDiv2">
                                    <textarea placeholder="Supporters" rows="5" cols="30"></textarea>
                                </div>
                            </div>
                            <div class="headingButton">
                                <button type="submit">Create Game!!</button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="tab-2">
                    <label for="tab2-2">Images</label>
                    <input id="tab2-2" name="tabs-two" type="radio">
                    <div class="fileInputDiv">
						<div class="inputDiv">
                            <label>+
                            <input type="file" onchange="previewFile()">
                            </label>
                            <br>
                        </div>
                    </div>
                </div>
            </div>
            </div>
        </div>
    </div>
</div>
<!--======CREATE GAME MODAL END======-->

 
<%@include file="includeBoostrapBottom.jsp" %>
</body>
</html>