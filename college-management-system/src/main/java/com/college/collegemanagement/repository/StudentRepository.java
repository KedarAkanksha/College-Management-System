package com.college.collegemanagement.repository;

import com.college.collegemanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByDepartmentId(int departmentId);
    List<Student> findByCourse(String course);
    Optional<Student> findByEmail(String email);
    boolean existsByEmail(String email);
}