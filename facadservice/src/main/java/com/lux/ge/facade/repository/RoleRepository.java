package com.lux.ge.facade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lux.ge.facade.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}