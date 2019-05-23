
<!--======SHOE LOGIN BUTTON======-->
    <div class="sticky">
    <div class="showLoginBtn position-fixed">
            <button id="showBtn" type="button" class="btn btn-outline-warning" data-toggle="modal" data-target="#loginDiv">LOGIN</button>
    </div>
    </div>
<!--======SHOE LOGIN BUTTON END======-->

<!--======POPUP LOG/REG======-->
    <div id="loginDiv" class="modal fade text-white" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog loginDiv">
            <div class="modal-content loginDiv">
        <!--======LOGIN/REG FORM======-->
                <div class="loginRegisterForms ">
            <!--======LOGIN FORM======-->
                        <div class="loginForm bg-dark text-light mx-auto active neonDiv p-4 w-75 bgBrick">
                            <button type="button" class="py-3 close" data-dismiss="modal" aria-label="Close">
                              <span aria-hidden="true" class="neonText">&times;</span>
                            </button>
                            <div class="mx-auto py-4 my-4 text-center"><p class="neonText">Friday<br/>Games</p></div>
                            <form id="loginForm" action="${pageContext.request.contextPath }/login" method="post" class="m-3 p-3 text-center">
                              <h1 class="h3 mb-3 font-weight-normal py-2 ">LOGIN</h1>
                              <label for="inputEmail" class="sr-only">Email address</label>
                              <input name="emailAddress" type="email" id="inputEmail" class="my-2 px-4 form-control" placeholder="Email address" required autofocus>
                              <label for="inputPassword" class=" sr-only">Password</label>
                              <input name="password" type="password" id="inputPassword" class="my-2 px-4 form-control" placeholder="Password" required>
                        </form>
                           <div class="row p-2 mx-2">
                             <div class="col-sm-6">
                                 <button id="loginBtn" class="btn btn-lg btn-danger btn-block text-white font-weight-bold " type="submit">Sign in</button>
                               </div>
                             <div class="col-sm-6">
                                 <button id="registerFormBtn" class="btn btn-lg btn-outline-warning btn-block text-white font-weight-bold" >Register</button>
                               </div>
                             </div>
                        </div>
            <!--======LOGIN FORM END======-->
            <!--======REGISTER FORM-->
                        <div class="registerForm bg-dark text-light mx-auto neonDiv p-4 w-75 bgBrick">
                            <button type="button" class="py-3 close" data-dismiss="modal" aria-label="Close">
                              <span aria-hidden="true">&times;</span>
                            </button>
                            <div class="mx-auto py-4 my-4 text-center"><p class="neonText">Friday<br/>Games</p></div>
                            <form id="registerForm" action="${pageContext.request.contextPath }/usercreate" method="post" class="m-3 p-3 text-center">
                              <h1 class="h3 mb-3 font-weight-normal py-2 ">REGISTER</h1>
                              <label for="inputEmail" class="sr-only">Email address</label>
                              <input name="inputEmail" type="email" id="inputEmail" class="my-2 px-4 form-control" placeholder="Email address" required autofocus>
                              <label for="inputFullName" class="sr-only">Full Name</label>
                              <input name="inputFullName" type="text" id="inputFullName" class="my-2 px-4 form-control" placeholder="Full Name" required >
                              <label for="inputPassword" class=" sr-only">Password</label>
                              <input name="inputPassword" type="password" id="inputPassword" class="my-2 px-4 form-control" placeholder="Password" required>
                            </form>
                              <div class="row p-2 mx-2">
                                <div class="col-sm-6">
                                   <button id="loginFormBtn" class="btn btn-lg btn-outline-warning  btn-block text-white font-weight-bold" type="submit">Sign in</button>
                                </div>
                                  <div class="col-sm-6">
                                    <button id="registerBtn" class="btn btn-lg btn-danger btn-block btn-danger text-white font-weight-bold" type="submit">Register</button>
                                  </div>
                            </div>
                        </div>
            <!--======REGISTER FORM END======-->
                </div>
        <!--======LOG/REG FORM END======-->
            </div>
        </div>
    </div>
    <div class="clearfix"></div>
<!--======POPUP LOG/REG END======-->
