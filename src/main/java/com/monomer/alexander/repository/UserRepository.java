package com.monomer.alexander.repository;

import com.monomer.alexander.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
