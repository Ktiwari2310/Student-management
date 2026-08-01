package com.tcet.student_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcet.student_management.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Long>{

}