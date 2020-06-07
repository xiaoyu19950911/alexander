package com.monomer.alexander.security.repository;

import com.monomer.alexander.security.entity.ResourcePermissionRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourcePermissionRelationRepository extends JpaRepository<ResourcePermissionRelation, Integer> {
}
