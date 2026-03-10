package com.college.collegemanagement.mapper;

import com.college.collegemanagement.dto.AttendanceDTO;
import com.college.collegemanagement.entity.Attendance;
import org.springframework.stereotype.Component;

@Component
public class AttendanceMapper {

    public AttendanceDTO toDTO(Attendance attendance) {
        if (attendance == null) {
            return null;
        }
        AttendanceDTO dto = new AttendanceDTO();
        dto.setAttendanceId(attendance.getAttendanceId());
        dto.setStudentId(attendance.getStudent() != null ? attendance.getStudent().getStudentId() : 0);
        dto.setTeacherId(attendance.getTeacher() != null ? attendance.getTeacher().getTeacherId() : 0);
        dto.setMarkId(attendance.getMarkId());
        dto.setAttendanceDate(attendance.getAttendanceDate());
        dto.setCheckInTime(attendance.getCheckInTime());
        dto.setCheckOutTime(attendance.getCheckOutTime());
        dto.setStatus(attendance.getStatus());
        return dto;
    }

    public Attendance toEntity(AttendanceDTO dto) {
        if (dto == null) {
            return null;
        }
        Attendance attendance = new Attendance();
        attendance.setAttendanceId(dto.getAttendanceId());
        attendance.setMarkId(dto.getMarkId());
        attendance.setAttendanceDate(dto.getAttendanceDate());
        attendance.setCheckInTime(dto.getCheckInTime());
        attendance.setCheckOutTime(dto.getCheckOutTime());
        attendance.setStatus(dto.getStatus());
        return attendance;
    }
}