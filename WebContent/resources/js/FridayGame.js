AOS.init({duration: 500});
$("#showBtn").click(function(){
//  $(".loginForm").toggleClass("showDiv");
//  $(".registerForm").toggleClass("showDiv");
//  $(".loginForm").toggleClass("rollin");
//  $(".loginForm").toggleClass("active");
});
$("#registerFormBtn").click(function(){
toggleForm();
});
$("#loginFormBtn").click(function(){
toggleForm();
});
function toggleForm(){
  $(".loginForm").toggleClass("active");
  $(".registerForm").toggleClass("active");
  $(".loginForm").toggleClass("rollin");
  $(".registerForm").toggleClass("rollin");
    
}
$( "#loginBtn" ).click(function() {
	  $( "#loginForm" ).submit();
});
$( "#registerBtn" ).click(function() {
	  $( "#registerForm" ).submit();
});
