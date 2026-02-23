package com.college.collegemanagement.mapper;

import com.college.collegemanagement.dto.AttendanceDTO;
import com.college.collegemanagement.entity.Attendance;
import org.springframework.stereotype.Component;

@Component
public class AttendanceMapper {

    // ===== ENTITY ➝ DTO =====
    public AttendanceDTO toDTO(Attendance a) {

        AttendanceDTO dto = new AttendanceDTO();

        dto.setStudentId(a.getStudentId());
        dto.setTeacherId(a.getTeacherId());
        dto.setMarkId(a.getMarkId());

        dto.setAttendanceDate(a.getAttendanceDate());
        dto.setCheckInTime(a.getCheckInTime());
        dto.setCheckOutTime(a.getCheckOutTime());
        dto.setStatus(a.getStatus());

        return dto;
    }

    // ===== DTO ➝ ENTITY =====
    public Attendance toEntity(AttendanceDTO dto) {

        Attendance a = new Attendance();

        a.setStudentId(dto.getStudentId());
        a.setTeacherId(dto.getTeacherId());
        a.setMarkId(dto.getMarkId());

        a.setAttendanceDate(dto.getAttendanceDate());
        a.setCheckInTime(dto.getCheckInTime());
        a.setCheckOutTime(dto.getCheckOutTime());
        a.setStatus(dto.getStatus());

        return a;
    }
}
