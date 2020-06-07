package com.monomer.alexander.security.repository;

import com.monomer.alexander.security.entity.UserRoleRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRelationRepository extends JpaRepository<UserRoleRelation, Integer> {

    List<UserRoleRelation> findAllByUserId(Integer userId);
}
