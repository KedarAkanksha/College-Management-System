package com.college.college_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.college.college_management_system", "com.college.collegemanagement"})
@EnableJpaRepositories(basePackages = "com.college.collegemanagement.repository")
@EntityScan(basePackages = "com.college.collegemanagement.entity")
public class CollegeManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollegeManagementSystemApplication.class, args);
        System.out.println("✅ College Management System Started Successfully...");
    }
}
