package com.monomer.alexander.security.repository;

import com.monomer.alexander.security.entity.RoleResourceRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleResourceRelationRepository extends JpaRepository<RoleResourceRelation, Integer> {

    List<RoleResourceRelation> findAllByRoleId(Integer roleId);
}
