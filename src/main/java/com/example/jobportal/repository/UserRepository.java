package com.example.jobportal.repository;

import com.example.jobportal.entities.Users;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);
}
