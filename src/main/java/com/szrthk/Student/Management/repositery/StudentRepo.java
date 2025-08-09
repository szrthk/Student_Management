package com.szrthk.Student.Management.repositery;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.szrthk.Student.Management.entity.Student;

import java.util.Optional;

public interface StudentRepo extends MongoRepository<Student, String> {
    Optional<Student> findByRollNumber(String rollNumber);
    Optional<Student> findByName(String name);
    void deleteByRollNumber(String rollNumber);
    boolean existsByRollNumber(String rollNumber);
}
