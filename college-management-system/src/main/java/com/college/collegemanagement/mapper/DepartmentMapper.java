package com.college.collegemanagement.mapper;

import com.college.collegemanagement.entity.Department;

public class DepartmentMapper {

    public static Department mapToDepartment(String name, String description) {

        Department department = new Department();
        department.setDepartmentName(name);
        department.setDescription(description);

        return department;
    }
}