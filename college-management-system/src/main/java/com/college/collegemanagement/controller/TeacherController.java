package com.college.collegemanagement.controller;

import com.college.collegemanagement.entity.Teacher;
import com.college.collegemanagement.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public Teacher register(@RequestBody Teacher teacher) {
        return service.registerTeacher(teacher);
    }

    @PutMapping("/update/{id}")
    public Teacher update(@PathVariable int id, @RequestBody Teacher teacher) {
        return service.updateTeacher(id, teacher);
    }

    @GetMapping("/{id}")
    public Teacher getOne(@PathVariable int id) {
        return service.getTeacherById(id);
    }

    @GetMapping("/all")
    public List<Teacher> getAll() {
        return service.getAllTeachers();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        service.deleteTeacher(id);
        return "Teacher Deleted Successfully";
    }
}
