package com.fdmgroup.model;

public enum ActiveStatus {
	INACTIVE("Deactive"),
	ACTIVE("Active");
	
	private String description;
	public String getDescription() {
		return description;
	}
	private ActiveStatus(String description) {
		this.description = description;
	}
	
}
