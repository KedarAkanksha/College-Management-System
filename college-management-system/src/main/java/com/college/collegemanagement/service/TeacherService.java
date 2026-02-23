package com.college.collegemanagement.service;

import com.college.collegemanagement.entity.Teacher;
import com.college.collegemanagement.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository repo;

    public TeacherService(TeacherRepository repo) {
        this.repo = repo;
    }

    // ✅ REGISTER TEACHER
    public Teacher registerTeacher(Teacher teacher) {

        if (repo.existsByEmail(teacher.getEmail())) {
            throw new RuntimeException("❌ Email Already Exists");
        }

        if (repo.existsByMobile(teacher.getMobile())) {
            throw new RuntimeException("❌ Mobile Number Already Exists");
        }

        teacher.setStatus("ACTIVE");

        return repo.save(teacher);
    }

    // ✅ UPDATE TEACHER
    public Teacher updateTeacher(int teacherId, Teacher newData) {

        Teacher old = repo.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher Not Found"));

        old.setFullName(newData.getFullName());
        old.setDepartment(newData.getDepartment());
        old.setSubject(newData.getSubject());
        old.setQualification(newData.getQualification());
        old.setExperience(newData.getExperience());
        old.setSalary(newData.getSalary());
        old.setStatus(newData.getStatus());

        return repo.save(old);
    }

    // ✅ DELETE TEACHER
    public void deleteTeacher(int teacherId) {
        repo.deleteById(teacherId);
    }

    // ✅ GET ONE TEACHER
    public Teacher getTeacherById(int teacherId) {
        return repo.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher Not Found"));
    }

    // ✅ GET ALL TEACHERS
    public List<Teacher> getAllTeachers() {
        return repo.findAll();
    }
}
