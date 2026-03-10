package com.college.collegemanagement.controller;

import com.college.collegemanagement.entity.Department;
import com.college.collegemanagement.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public Department save(@RequestBody Department department) {
        return service.saveDepartment(department);
    }

    @GetMapping("/all")
    public List<Department> getAll() {
        return service.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getOne(@PathVariable int id) {
        return service.getDepartmentById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        service.deleteDepartment(id);
        return "Department Deleted";
    }
}