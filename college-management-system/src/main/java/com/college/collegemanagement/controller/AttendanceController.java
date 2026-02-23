package com.college.collegemanagement.controller;

import com.college.collegemanagement.dto.AttendanceDTO;
import com.college.collegemanagement.entity.Attendance;
import com.college.collegemanagement.service.AttendanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService service;

    public AttendanceController(AttendanceService service) {
        this.service = service;
    }

    // ✅ MARK ATTENDANCE
    @PostMapping("/mark")
    public Attendance mark(@RequestBody AttendanceDTO dto) {
        return service.markAttendance(dto);
    }

    // ✅ CHECK OUT
    @PutMapping("/checkout/{id}")
    public Attendance checkOut(@PathVariable int id) {
        return service.checkOut(id);
    }

    // ✅ GET ALL
    @GetMapping("/all")
    public List<Attendance> all() {
        return service.getAll();
    }

    // ✅ GET STUDENT ATTENDANCE
    @GetMapping("/student/{studentId}")
    public List<Attendance> student(@PathVariable int studentId) {
        return service.getStudentAttendance(studentId);
    }

    // ✅ GET TEACHER ATTENDANCE
    @GetMapping("/teacher/{teacherId}")
    public List<Attendance> teacher(@PathVariable int teacherId) {
        return service.getTeacherAttendance(teacherId);
    }
}
