package com.college.collegemanagement.service;

import com.college.collegemanagement.dto.AttendanceDTO;
import com.college.collegemanagement.entity.Attendance;
import com.college.collegemanagement.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository repo;

    public AttendanceService(AttendanceRepository repo) {
        this.repo = repo;
    }

    // ✅ SAVE ATTENDANCE
    public Attendance markAttendance(AttendanceDTO dto) {

        Attendance a = new Attendance();

        a.setStudentId(dto.getStudentId());
        a.setTeacherId(dto.getTeacherId());
        a.setMarkId(dto.getMarkId());

        a.setAttendanceDate(dto.getAttendanceDate() == null ? LocalDate.now() : dto.getAttendanceDate());
        a.setCheckInTime(dto.getCheckInTime() == null ? LocalTime.now() : dto.getCheckInTime());
        a.setCheckOutTime(dto.getCheckOutTime());

        a.setStatus(dto.getStatus() == null ? "PRESENT" : dto.getStatus());

        return repo.save(a);
    }

    // ✅ CHECK OUT UPDATE
    public Attendance checkOut(int id) {
        Attendance a = repo.findById(id).orElseThrow();
        a.setCheckOutTime(LocalTime.now());
        return repo.save(a);
    }

    // ✅ GET ALL
    public List<Attendance> getAll() {
        return repo.findAll();
    }

    // ✅ GET STUDENT ATTENDANCE
    public List<Attendance> getStudentAttendance(int studentId) {
        return repo.findByStudentId(studentId);
    }

    // ✅ GET TEACHER ATTENDANCE
    public List<Attendance> getTeacherAttendance(int teacherId) {
        return repo.findByTeacherId(teacherId);
    }
}
