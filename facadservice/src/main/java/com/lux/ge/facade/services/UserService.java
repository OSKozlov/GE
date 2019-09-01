package com.lux.ge.facade.services;

import com.lux.ge.facade.model.User;

public interface UserService {
	
    void save(User user);
    
    User findByUsername(String username);
}
