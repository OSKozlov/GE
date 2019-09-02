package com.lux.ge.facade.model;

public enum RoleType {
	
	ENGINEER_USER("ENGINEER_USER"),
	ADMIN_USER("ADMIN_USER");
	
	private String roleName;
	
	private RoleType(String name) {
		this.roleName = name;
	}

	public String getRoleName() {
		return roleName;
	};

}
