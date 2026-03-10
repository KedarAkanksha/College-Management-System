package com.college.collegemanagement.repository;

import com.college.collegemanagement.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    List<Teacher> findByDepartmentId(int departmentId);
    List<Teacher> findByStatus(String status);
    Optional<Teacher> findByEmail(String email);
    boolean existsByEmail(String email);
}