package com.monomer.alexander.security.repository;

import com.monomer.alexander.security.entity.Auths;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthsRepository  extends JpaRepository<Auths, Integer> {

    Auths findFirstByIdentifier(String identifier);
}
