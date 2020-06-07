package com.monomer.alexander.security.repository;

import com.monomer.alexander.security.entity.RoleResourceRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleResourceRelationRepository extends JpaRepository<RoleResourceRelation, Integer> {
}
