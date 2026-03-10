package com.college.collegemanagement.repository;

import com.college.collegemanagement.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    List<Attendance> findByTeacherId(int teacherId);
    List<Attendance> findByStudentId(int studentId);
    List<Attendance> findByAttendanceDate(LocalDate date);
    List<Attendance> findByTeacherIdAndAttendanceDate(int teacherId, LocalDate date);
    List<Attendance> findByStudentIdAndAttendanceDate(int studentId, LocalDate date);
    List<Attendance> findByTeacherIdAndStudentId(int teacherId, int studentId);
}