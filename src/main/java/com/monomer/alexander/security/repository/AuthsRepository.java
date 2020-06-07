package com.monomer.alexander.security.repository;

import com.monomer.alexander.security.entity.Auths;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthsRepository  extends JpaRepository<Auths, Integer> {

    List<Auths> findAllByIdentifierAndIdentityType(String identifier,String identityType);
}
