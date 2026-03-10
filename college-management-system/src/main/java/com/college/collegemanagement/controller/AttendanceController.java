package com.college.collegemanagement.controller;

import com.college.collegemanagement.dto.AttendanceDTO;
import com.college.collegemanagement.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "*")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // MARK ATTENDANCE
    @PostMapping("/mark")
    public ResponseEntity<AttendanceDTO> markAttendance(@RequestBody AttendanceDTO attendanceDTO) {
        try {
            AttendanceDTO savedAttendance = attendanceService.markAttendance(attendanceDTO);
            return new ResponseEntity<>(savedAttendance, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // GET ALL ATTENDANCE
    @GetMapping("/all")
    public ResponseEntity<List<AttendanceDTO>> getAllAttendance() {
        List<AttendanceDTO> attendance = attendanceService.getAllAttendance();
        return new ResponseEntity<>(attendance, HttpStatus.OK);
    }

    // GET ATTENDANCE BY TEACHER
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceByTeacher(@PathVariable int teacherId) {
        List<AttendanceDTO> attendance = attendanceService.getAttendanceByTeacher(teacherId);
        return new ResponseEntity<>(attendance, HttpStatus.OK);
    }

    // GET ATTENDANCE BY STUDENT
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceByStudent(@PathVariable int studentId) {
        List<AttendanceDTO> attendance = attendanceService.getAttendanceByStudent(studentId);
        return new ResponseEntity<>(attendance, HttpStatus.OK);
    }

    // GET ATTENDANCE BY DATE
    @GetMapping("/date/{date}")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceByDate(@PathVariable LocalDate date) {
        List<AttendanceDTO> attendance = attendanceService.getAttendanceByDate(date);
        return new ResponseEntity<>(attendance, HttpStatus.OK);
    }

    // GET ATTENDANCE BY TEACHER AND DATE
    @GetMapping("/teacher/{teacherId}/date/{date}")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceByTeacherAndDate(
            @PathVariable int teacherId, @PathVariable LocalDate date) {
        List<AttendanceDTO> attendance = attendanceService.getAttendanceByTeacherAndDate(teacherId, date);
        return new ResponseEntity<>(attendance, HttpStatus.OK);
    }

    // GET ATTENDANCE BY STUDENT AND DATE
    @GetMapping("/student/{studentId}/date/{date}")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceByStudentAndDate(
            @PathVariable int studentId, @PathVariable LocalDate date) {
        List<AttendanceDTO> attendance = attendanceService.getAttendanceByStudentAndDate(studentId, date);
        return new ResponseEntity<>(attendance, HttpStatus.OK);
    }

    // GET ATTENDANCE BY TEACHER AND STUDENT
    @GetMapping("/teacher/{teacherId}/student/{studentId}")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceByTeacherAndStudent(
            @PathVariable int teacherId, @PathVariable int studentId) {
        List<AttendanceDTO> attendance = attendanceService.getAttendanceByTeacherAndStudent(teacherId, studentId);
        return new ResponseEntity<>(attendance, HttpStatus.OK);
    }

    // UPDATE ATTENDANCE
    @PutMapping("/update/{attendanceId}")
    public ResponseEntity<AttendanceDTO> updateAttendance(
            @PathVariable int attendanceId, @RequestBody AttendanceDTO attendanceDTO) {
        try {
            AttendanceDTO updatedAttendance = attendanceService.updateAttendance(attendanceId, attendanceDTO);
            return new ResponseEntity<>(updatedAttendance, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE ATTENDANCE
    @DeleteMapping("/delete/{attendanceId}")
    public ResponseEntity<String> deleteAttendance(@PathVariable int attendanceId) {
        try {
            attendanceService.deleteAttendance(attendanceId);
            return new ResponseEntity<>("Attendance Deleted Successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}