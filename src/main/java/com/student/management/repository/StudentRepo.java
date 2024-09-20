package com.student.management.repository;

import com.student.management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    public Student findByStudentName(String name);
    public Optional<Student> findByStudentEmail(String email);

}
