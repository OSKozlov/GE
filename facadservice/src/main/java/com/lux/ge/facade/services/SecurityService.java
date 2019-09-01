package com.lux.ge.facade.services;

public interface SecurityService {

	String findLoggedInUsername();
	
	void autoLogin(String username, String password);
	
}
