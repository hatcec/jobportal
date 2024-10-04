package com.example.jobportal.repository;

import com.example.jobportal.entities.JobSeekerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSeekerProfileRepository extends JpaRepository<JobSeekerProfile , Integer> {
}
