package com.college.collegemanagement.entity;

import jakarta.persistence.*;

@Entity
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feeId;

    private double totalFee;
    private double paidFee;
    private double pendingFee;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    public int getFeeId() {
        return feeId;
    }

    public void setFeeId(int feeId) {
        this.feeId = feeId;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public double getPaidFee() {
        return paidFee;
    }

    public void setPaidFee(double paidFee) {
        this.paidFee = paidFee;
    }

    public double getPendingFee() {
        return pendingFee;
    }

    public void setPendingFee(double pendingFee) {
        this.pendingFee = pendingFee;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}