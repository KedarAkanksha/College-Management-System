package com.college.collegemanagement.repository;

import com.college.collegemanagement.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    List<Attendance> findByStudentId(int studentId);
    List<Attendance> findByTeacherId(int teacherId);
    List<Attendance> findByAttendanceDate(LocalDate date);
}
