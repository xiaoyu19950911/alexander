package com.monomer.alexander.security.repository;

import com.monomer.alexander.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository  extends JpaRepository<Role, Integer> {

}
