package com.college.collegemanagement.service;

import com.college.collegemanagement.dto.TeacherDTO;
import com.college.collegemanagement.entity.Department;
import com.college.collegemanagement.entity.Teacher;
import com.college.collegemanagement.mapper.TeacherMapper;
import com.college.collegemanagement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Create Teacher (Register)
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        if (teacherRepository.existsByEmail(teacherDTO.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }

        Teacher teacher = teacherMapper.toEntity(teacherDTO);
        teacher.setPassword(passwordEncoder.encode(teacherDTO.getPassword()));

        Department department = departmentService.getDepartmentById(teacherDTO.getDepartmentId());
        teacher.setDepartment(department);

        Teacher savedTeacher = teacherRepository.save(teacher);
        return teacherMapper.toDTO(savedTeacher);
    }

    // Login Teacher
    public TeacherDTO loginTeacher(String email, String password) {
        Optional<Teacher> teacherOpt = teacherRepository.findByEmail(email);
        if (teacherOpt.isEmpty()) {
            throw new RuntimeException("Teacher not found!");
        }

        Teacher teacher = teacherOpt.get();
        if (!passwordEncoder.matches(password, teacher.getPassword())) {
            throw new RuntimeException("Invalid password!");
        }

        return teacherMapper.toDTO(teacher);
    }

    // Get All Teachers
    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(teacherMapper::toDTO)
                .toList();
    }

    // Get Teacher by ID
    public TeacherDTO getTeacherById(int teacherId) {
        return teacherRepository.findById(teacherId)
                .map(teacherMapper::toDTO)
                .orElse(null);
    }

    // Update Teacher
    public TeacherDTO updateTeacher(int teacherId, TeacherDTO teacherDTO) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found!"));

        teacher.setFullName(teacherDTO.getFullName());
        teacher.setEmail(teacherDTO.getEmail());
        teacher.setMobile(teacherDTO.getMobile());
        teacher.setGender(teacherDTO.getGender());
        teacher.setDob(teacherDTO.getDob());
        teacher.setQualification(teacherDTO.getQualification());
        teacher.setExperience(teacherDTO.getExperience());
        teacher.setSubject(teacherDTO.getSubject());
        teacher.setSalary(teacherDTO.getSalary());
        teacher.setAadhaarNumber(teacherDTO.getAadhaarNumber());
        teacher.setAddress(teacherDTO.getAddress());
        teacher.setJoiningDate(teacherDTO.getJoiningDate());
        teacher.setStatus(teacherDTO.getStatus());

        if (teacherDTO.getDepartmentId() != 0) {
            Department department = departmentService.getDepartmentById(teacherDTO.getDepartmentId());
            teacher.setDepartment(department);
        }

        Teacher updatedTeacher = teacherRepository.save(teacher);
        return teacherMapper.toDTO(updatedTeacher);
    }

    // Delete Teacher
    public void deleteTeacher(int teacherId) {
        if (!teacherRepository.existsById(teacherId)) {
            throw new RuntimeException("Teacher not found!");
        }
        teacherRepository.deleteById(teacherId);
    }
}