package com.example.jobportal.repository;

import com.example.jobportal.entities.Users;
import com.example.jobportal.entities.UsersType;
import com.example.jobportal.services.UsersTypeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.JpaParameters;

public interface UsersTypeRepository extends JpaRepository<UsersType, Integer> {
}
