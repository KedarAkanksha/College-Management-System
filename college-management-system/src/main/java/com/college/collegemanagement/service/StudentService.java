package com.college.collegemanagement.service;

import com.college.collegemanagement.dto.StudentDTO;
import com.college.collegemanagement.entity.Department;
import com.college.collegemanagement.entity.Student;
import com.college.collegemanagement.mapper.StudentMapper;
import com.college.collegemanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private StudentMapper studentMapper;

    // Create Student (Register)
    public StudentDTO createStudent(StudentDTO studentDTO) {
        if (studentDTO == null) {
            throw new RuntimeException("Student data cannot be null!");
        }
        
        if (studentRepository.existsByEmail(studentDTO.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }

        Student student = studentMapper.toEntity(studentDTO);
        // Store password as plain text (for demo purposes)
        student.setPassword(studentDTO.getPassword());

        Department department = departmentService.getDepartmentById(studentDTO.getDepartmentId());
        student.setDepartment(department);

        Student savedStudent = studentRepository.save(student);
        return studentMapper.toDTO(savedStudent);
    }

    // Login Student
    public StudentDTO loginStudent(String email, String password) {
        if (email == null || password == null) {
            throw new RuntimeException("Email and password cannot be null!");
        }

        Optional<Student> studentOpt = studentRepository.findByEmail(email);
        if (studentOpt.isEmpty()) {
            throw new RuntimeException("Student not found!");
        }

        Student student = studentOpt.get();
        // Simple password comparison (plain text)
        if (!password.equals(student.getPassword())) {
            throw new RuntimeException("Invalid password!");
        }

        return studentMapper.toDTO(student);
    }

    // Get All Students
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(studentMapper::toDTO)
                .toList();
    }

    // Get Student by ID
    public StudentDTO getStudentById(int studentId) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        if (studentOpt.isEmpty()) {
            return null;
        }
        return studentMapper.toDTO(studentOpt.get());
    }

    // Update Student
    public StudentDTO updateStudent(int studentId, StudentDTO studentDTO) {
        if (studentDTO == null) {
            throw new RuntimeException("Student data cannot be null!");
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        student.setFullName(studentDTO.getFullName());
        student.setEmail(studentDTO.getEmail());
        student.setMobile(studentDTO.getMobile());
        student.setCourse(studentDTO.getCourse());
        student.setFatherName(studentDTO.getFatherName());
        student.setMotherName(studentDTO.getMotherName());
        student.setFatherMobile(studentDTO.getFatherMobile());
        student.setMotherMobile(studentDTO.getMotherMobile());
        student.setAddress(studentDTO.getAddress());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setEducation(studentDTO.getEducation());

        if (studentDTO.getDepartmentId() != 0) {
            Department department = departmentService.getDepartmentById(studentDTO.getDepartmentId());
            student.setDepartment(department);
        }

        Student updatedStudent = studentRepository.save(student);
        return studentMapper.toDTO(updatedStudent);
    }

    // Delete Student
    public void deleteStudent(int studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new RuntimeException("Student not found with id: " + studentId);
        }
        studentRepository.deleteById(studentId);
    }
}