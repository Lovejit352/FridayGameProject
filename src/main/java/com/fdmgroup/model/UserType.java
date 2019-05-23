package com.fdmgroup.model;

public enum UserType {
	ADMIN("Admin"),
	GAMEMANAGER("Game Manager"),
	TRAINEE("Trainee");
	
	private String description;
	
	public String getDescription() {
		return description;
	}

	private UserType(String description) {
		this.description = description;
	}
}
 