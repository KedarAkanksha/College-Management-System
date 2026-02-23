package com.college.collegemanagement.mapper;

import com.college.collegemanagement.dto.TeacherDTO;
import com.college.collegemanagement.entity.Teacher;

public class TeacherMapper {

    // Entity → DTO
    public static TeacherDTO toDTO(Teacher teacher) {
        TeacherDTO dto = new TeacherDTO();

        dto.setTeacherId(teacher.getTeacherId());
        dto.setFullName(teacher.getFullName());
        dto.setEmail(teacher.getEmail());
        dto.setMobile(teacher.getMobile());
        dto.setGender(teacher.getGender());
        dto.setDob(teacher.getDob());
        dto.setQualification(teacher.getQualification());
        dto.setExperience(teacher.getExperience());
        dto.setDepartment(teacher.getDepartment());
        dto.setSubject(teacher.getSubject());
        dto.setSalary(teacher.getSalary());
        dto.setAddress(teacher.getAddress());
        dto.setJoiningDate(teacher.getJoiningDate());
        dto.setStatus(teacher.getStatus());

        return dto;
    }

    // DTO → Entity
    public static Teacher toEntity(TeacherDTO dto) {
        Teacher teacher = new Teacher();

        teacher.setTeacherId(dto.getTeacherId());
        teacher.setFullName(dto.getFullName());
        teacher.setEmail(dto.getEmail());
        teacher.setMobile(dto.getMobile());
        teacher.setGender(dto.getGender());
        teacher.setDob(dto.getDob());
        teacher.setQualification(dto.getQualification());
        teacher.setExperience(dto.getExperience());
        teacher.setDepartment(dto.getDepartment());
        teacher.setSubject(dto.getSubject());
        teacher.setSalary(dto.getSalary());
        teacher.setAddress(dto.getAddress());
        teacher.setJoiningDate(dto.getJoiningDate());
        teacher.setStatus(dto.getStatus());

        return teacher;
    }
}
