package com.college.collegemanagement.service;

import com.college.collegemanagement.dto.AttendanceDTO;
import com.college.collegemanagement.entity.Attendance;
import com.college.collegemanagement.entity.Student;
import com.college.collegemanagement.entity.Teacher;
import com.college.collegemanagement.mapper.AttendanceMapper;
import com.college.collegemanagement.repository.AttendanceRepository;
import com.college.collegemanagement.repository.StudentRepository;
import com.college.collegemanagement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    // MARK ATTENDANCE (Teacher marks for student)
    public AttendanceDTO markAttendance(AttendanceDTO attendanceDTO) {
        Attendance attendance = new Attendance();
        
        // Get Student Entity directly from Repository
        Student student = studentRepository.findById(attendanceDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + attendanceDTO.getStudentId()));
        
        // Get Teacher Entity directly from Repository
        Teacher teacher = teacherRepository.findById(attendanceDTO.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + attendanceDTO.getTeacherId()));
        
        attendance.setStudent(student);
        attendance.setTeacher(teacher);
        attendance.setMarkId(attendanceDTO.getMarkId());
        
        if (attendanceDTO.getAttendanceDate() == null) {
            attendance.setAttendanceDate(LocalDate.now());
        } else {
            attendance.setAttendanceDate(attendanceDTO.getAttendanceDate());
        }
        
        if (attendanceDTO.getCheckInTime() == null) {
            attendance.setCheckInTime(LocalTime.now());
        } else {
            attendance.setCheckInTime(attendanceDTO.getCheckInTime());
        }
        
        if (attendanceDTO.getCheckOutTime() == null) {
            attendance.setCheckOutTime(LocalTime.now());
        } else {
            attendance.setCheckOutTime(attendanceDTO.getCheckOutTime());
        }
        
        if (attendanceDTO.getStatus() == null) {
            attendance.setStatus("PRESENT");
        } else {
            attendance.setStatus(attendanceDTO.getStatus());
        }

        Attendance savedAttendance = attendanceRepository.save(attendance);
        return attendanceMapper.toDTO(savedAttendance);
    }

    // GET ALL ATTENDANCE
    public List<AttendanceDTO> getAllAttendance() {
        return attendanceRepository.findAll().stream()
                .map(attendanceMapper::toDTO)
                .toList();
    }

    // GET ATTENDANCE BY TEACHER
    public List<AttendanceDTO> getAttendanceByTeacher(int teacherId) {
        return attendanceRepository.findByTeacherId(teacherId).stream()
                .map(attendanceMapper::toDTO)
                .toList();
    }

    // GET ATTENDANCE BY STUDENT
    public List<AttendanceDTO> getAttendanceByStudent(int studentId) {
        return attendanceRepository.findByStudentId(studentId).stream()
                .map(attendanceMapper::toDTO)
                .toList();
    }

    // GET ATTENDANCE BY DATE
    public List<AttendanceDTO> getAttendanceByDate(LocalDate date) {
        return attendanceRepository.findByAttendanceDate(date).stream()
                .map(attendanceMapper::toDTO)
                .toList();
    }

    // GET ATTENDANCE BY TEACHER AND DATE
    public List<AttendanceDTO> getAttendanceByTeacherAndDate(int teacherId, LocalDate date) {
        return attendanceRepository.findByTeacherIdAndAttendanceDate(teacherId, date).stream()
                .map(attendanceMapper::toDTO)
                .toList();
    }

    // GET ATTENDANCE BY STUDENT AND DATE
    public List<AttendanceDTO> getAttendanceByStudentAndDate(int studentId, LocalDate date) {
        return attendanceRepository.findByStudentIdAndAttendanceDate(studentId, date).stream()
                .map(attendanceMapper::toDTO)
                .toList();
    }

    // GET ATTENDANCE BY TEACHER AND STUDENT
    public List<AttendanceDTO> getAttendanceByTeacherAndStudent(int teacherId, int studentId) {
        return attendanceRepository.findByTeacherIdAndStudentId(teacherId, studentId).stream()
                .map(attendanceMapper::toDTO)
                .toList();
    }

    // UPDATE ATTENDANCE
    public AttendanceDTO updateAttendance(int attendanceId, AttendanceDTO attendanceDTO) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + attendanceId));

        if (attendanceDTO.getStudentId() > 0) {
            Student student = studentRepository.findById(attendanceDTO.getStudentId())
                    .orElse(null);
            if (student != null) {
                attendance.setStudent(student);
            }
        }

        if (attendanceDTO.getTeacherId() > 0) {
            Teacher teacher = teacherRepository.findById(attendanceDTO.getTeacherId())
                    .orElse(null);
            if (teacher != null) {
                attendance.setTeacher(teacher);
            }
        }

        attendance.setMarkId(attendanceDTO.getMarkId());
        attendance.setAttendanceDate(attendanceDTO.getAttendanceDate());
        attendance.setCheckInTime(attendanceDTO.getCheckInTime());
        attendance.setCheckOutTime(attendanceDTO.getCheckOutTime());
        attendance.setStatus(attendanceDTO.getStatus());

        Attendance updatedAttendance = attendanceRepository.save(attendance);
        return attendanceMapper.toDTO(updatedAttendance);
    }

    // DELETE ATTENDANCE
    public void deleteAttendance(int attendanceId) {
        if (!attendanceRepository.existsById(attendanceId)) {
            throw new RuntimeException("Attendance not found with id: " + attendanceId);
        }
        attendanceRepository.deleteById(attendanceId);
    }
}