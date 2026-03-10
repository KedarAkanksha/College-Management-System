package com.college.collegemanagement.mapper;

import com.college.collegemanagement.dto.StudentDTO;
import com.college.collegemanagement.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentDTO toDTO(Student student) {
        if (student == null) {
            return null;
        }
        StudentDTO dto = new StudentDTO();
        dto.setStudentId(student.getStudentId());
        dto.setFullName(student.getFullName());
        dto.setEmail(student.getEmail());
        dto.setMobile(student.getMobile());
        dto.setCourse(student.getCourse());
        dto.setFatherName(student.getFatherName());
        dto.setMotherName(student.getMotherName());
        dto.setFatherMobile(student.getFatherMobile());
        dto.setMotherMobile(student.getMotherMobile());
        dto.setAddress(student.getAddress());
        dto.setDateOfBirth(student.getDateOfBirth());
        dto.setEducation(student.getEducation());
        if (student.getDepartment() != null) {
            dto.setDepartmentId(student.getDepartment().getDepartmentId());
        }
        return dto;
    }

    public Student toEntity(StudentDTO dto) {
        if (dto == null) {
            return null;
        }
        Student student = new Student();
        student.setStudentId(dto.getStudentId());
        student.setFullName(dto.getFullName());
        student.setEmail(dto.getEmail());
        student.setMobile(dto.getMobile());
        student.setCourse(dto.getCourse());
        student.setFatherName(dto.getFatherName());
        student.setMotherName(dto.getMotherName());
        student.setFatherMobile(dto.getFatherMobile());
        student.setMotherMobile(dto.getMotherMobile());
        student.setAddress(dto.getAddress());
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setEducation(dto.getEducation());
        return student;
    }
}