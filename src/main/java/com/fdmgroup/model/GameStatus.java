package com.fdmgroup.model;

public enum GameStatus {
	CREATED("Created"),
	SUBMITTED("Submitted"),
	SELECTED("Selected"),
	PLAYED("Played");
	
	private String description;
	public String getDescription() {
		return description;
	}
	private GameStatus(String description) {
		this.description = description;
	}
}
