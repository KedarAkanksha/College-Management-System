package com.college.collegemanagement.mapper;

import com.college.collegemanagement.dto.TeacherDTO;
import com.college.collegemanagement.entity.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {

    public TeacherDTO toDTO(Teacher teacher) {
        if (teacher == null) {
            return null;
        }
        TeacherDTO dto = new TeacherDTO();
        dto.setTeacherId(teacher.getTeacherId());
        dto.setFullName(teacher.getFullName());
        dto.setEmail(teacher.getEmail());
        dto.setMobile(teacher.getMobile());
        dto.setGender(teacher.getGender());
        dto.setDob(teacher.getDob());
        dto.setQualification(teacher.getQualification());
        dto.setExperience(teacher.getExperience());
        dto.setSubject(teacher.getSubject());
        dto.setSalary(teacher.getSalary());
        dto.setAadhaarNumber(teacher.getAadhaarNumber());
        dto.setAddress(teacher.getAddress());
        dto.setJoiningDate(teacher.getJoiningDate());
        dto.setStatus(teacher.getStatus());
        if (teacher.getDepartment() != null) {
            dto.setDepartmentId(teacher.getDepartment().getDepartmentId());
        }
        return dto;
    }

    public Teacher toEntity(TeacherDTO dto) {
        if (dto == null) {
            return null;
        }
        Teacher teacher = new Teacher();
        teacher.setTeacherId(dto.getTeacherId());
        teacher.setFullName(dto.getFullName());
        teacher.setEmail(dto.getEmail());
        teacher.setMobile(dto.getMobile());
        teacher.setGender(dto.getGender());
        teacher.setDob(dto.getDob());
        teacher.setQualification(dto.getQualification());
        teacher.setExperience(dto.getExperience());
        teacher.setSubject(dto.getSubject());
        teacher.setSalary(dto.getSalary());
        teacher.setAadhaarNumber(dto.getAadhaarNumber());
        teacher.setAddress(dto.getAddress());
        teacher.setJoiningDate(dto.getJoiningDate());
        teacher.setStatus(dto.getStatus());
        return teacher;
    }
}