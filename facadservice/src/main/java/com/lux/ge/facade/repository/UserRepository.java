package com.lux.ge.facade.repository;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lux.ge.facade.model.User;


public interface UserRepository extends CrudRepository<User, Long> {

	/*
	 * Method finds User by email and status
	 * 
	 * @param email
	 * @return A User data
	 */
	@Query("select u from auth_user u where u.email=:email")
    User findByEmail(@Param("email") String email);
	
	
	/*
	 * select email, password from auth_user where email='olek.kozlov@gmail.com' and
	 * status='VERIFIED' select email, role_name from auth_user left outer join
	 * auth_role on auth_user.role = auth_role.role_name where
	 * auth_user.email='olek.kozlov@gmail.com' and status='VERIFIED';
	 */
	
}
