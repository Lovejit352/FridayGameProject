<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--======NAV BAR=======-->
<nav class="navbar neonDivNoAniNav navBg sticky-top navbar-expand-lg navbar-dark bgAlpha">
    <div class="container-fluid">
      <p class="neonTextLogo m-0 p-0">Friday Game Manager</p>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto ">
         
          <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}">Dashboard
              <span class="sr-only">(current)</span>
            </a>
          </li>
          <c:if test="${sessionUser.userType eq 'ADMIN' }">
	          <li class="nav-item">
	            <a class="nav-link" href="${pageContext.request.contextPath }/closeVoting">Close Voting</a>
	          </li>
          
          </c:if>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath }/pastgames/0">Past Game</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath }/votingList">Voting List</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle neonTextUsername" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              ${sessionUser.fullName }
            </a>
            <div class="dropdown-menu neonDivNoAni dropdown-menu-right alphaBlack text-light p-2 text-center" aria-labelledby="navbarDropdownMenuLink">
                <a class="nav-item neonTextUsername" href="#" data-toggle="modal" data-target="#changeName">Change name</a>
                <a class="nav-item neonTextUsername text-nowrap p-2" href="#" data-toggle="modal" data-target="#changePassword">Change Password</a>
                <a class="nav-item neonTextUsername" href="${pageContext.request.contextPath }/logout" >Logout</a>
            </div>
          </li>
        </ul>
      </div>
      
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
    </div>
</nav>
<div class="modal fade" id="changeName" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content neonDiv text-light bgBrick">
      <div class="modal-header">
        <h5 class="modal-title neonText">Change Name</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form method="post" action="userchangename" id="nameform">
            <label for="recipient-name" class="col-form-label">Current name:</label>
            <p class="">${sessionUser.fullName }</p>
            <label for="message-text" class="col-form-label">New Name:</label>
            <input type="text" class="form-control" id="newName" name="fullname"/>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary" form="nameform">Submit</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="changePassword" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content  neonDiv text-light bgBrick">
      <div class="modal-header">
        <h5 class="modal-title neonText">Change Password</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form action="changepassword" method="post" id="passwordform">
            <label for="currentPwd" class="col-form-label">Current Password:</label>
            <input type="text" class="form-control" id="currentPwd" name="currentPassword"/>
            <label for="newPwd" class="col-form-label">New Password:</label>
            <input type="text" class="form-control" id="newPwd" name="newPassword"/>
            <label for="confirmPwd" class="col-form-label">Confirm Password:</label>
            <input type="text" class="form-control" id="confirmPwd" name="confirmNewPassword"/>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary" form="passwordform">Submit</button>
      </div>
    </div>
  </div>
</div>
<!--navbar ends-->
