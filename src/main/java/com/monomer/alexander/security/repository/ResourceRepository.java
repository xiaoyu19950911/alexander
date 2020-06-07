package com.monomer.alexander.security.repository;

import com.monomer.alexander.security.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource,Integer> {
}
