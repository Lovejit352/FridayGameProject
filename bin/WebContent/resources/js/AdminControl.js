function editUser(name, email, type, id) {
	document.getElementById("fullName").value = name;
	document.getElementById("emailAddress").value = email;
	var select = document.getElementById("userTypes");
	var types = select.options;
	
	if (type == "ADMIN") {
		types.selectedIndex = 0;
	}
	
	if (type == "GAMEMANAGER") {
		types.selectedIndex = 1;
	}
	
	if (type == "TRAINEE") {
		types.selectedIndex = 2;
	}
	
	document.getElementById("edituserid").value = id;

}