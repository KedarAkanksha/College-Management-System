package com.college.collegemanagement.controller;

import com.college.collegemanagement.dto.TeacherDTO;
import com.college.collegemanagement.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@CrossOrigin(origins = "*")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // Create Teacher (Register)
    @PostMapping("/register")
    public ResponseEntity<TeacherDTO> registerTeacher(@RequestBody TeacherDTO teacherDTO) {
        try {
            TeacherDTO savedTeacher = teacherService.createTeacher(teacherDTO);
            return new ResponseEntity<>(savedTeacher, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Teacher Login
    @PostMapping("/login")
    public ResponseEntity<TeacherDTO> loginTeacher(@RequestBody TeacherDTO loginDTO) {
        try {
            TeacherDTO teacher = teacherService.loginTeacher(loginDTO.getEmail(), loginDTO.getPassword());
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // Get All Teachers
    @GetMapping("/all")
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        List<TeacherDTO> teachers = teacherService.getAllTeachers();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    // Get Teacher by ID
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable int id) {
        TeacherDTO teacher = teacherService.getTeacherById(id);
        if (teacher == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    // Update Teacher
    @PutMapping("/update/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable int id, @RequestBody TeacherDTO teacherDTO) {
        try {
            TeacherDTO updatedTeacher = teacherService.updateTeacher(id, teacherDTO);
            return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete Teacher
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable int id) {
        try {
            teacherService.deleteTeacher(id);
            return new ResponseEntity<>("Teacher Deleted Successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}