package com.college.collegemanagement.repository;

import com.college.collegemanagement.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    boolean existsByEmail(String email);
    boolean existsByMobile(String mobile);
}
