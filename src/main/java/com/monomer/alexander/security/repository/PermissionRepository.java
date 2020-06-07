package com.monomer.alexander.security.repository;

import com.monomer.alexander.security.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}
