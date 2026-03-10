package com.college.collegemanagement.dto;

import java.time.LocalDate;

public class StudentDTO {
    private int studentId;
    private String fullName;
    private String email;
    private String mobile;
    private String course;
    private String password;
    private String fatherName;
    private String motherName;
    private String fatherMobile;
    private String motherMobile;
    private String address;
    private LocalDate dateOfBirth;
    private String education;
    private int departmentId;

    // Getters and Setters
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getFatherName() { return fatherName; }
    public void setFatherName(String fatherName) { this.fatherName = fatherName; }
    public String getMotherName() { return motherName; }
    public void setMotherName(String motherName) { this.motherName = motherName; }
    public String getFatherMobile() { return fatherMobile; }
    public void setFatherMobile(String fatherMobile) { this.fatherMobile = fatherMobile; }
    public String getMotherMobile() { return motherMobile; }
    public void setMotherMobile(String motherMobile) { this.motherMobile = motherMobile; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public String getEducation() { return education; }
    public void setEducation(String education) { this.education = education; }
    public int getDepartmentId() { return departmentId; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }
}