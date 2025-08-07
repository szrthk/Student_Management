package com.szrthk.Student.Management.repositery;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.szrthk.Student.Management.entity.Course;

import java.util.Optional;

public interface CourseRepo extends MongoRepository<Course, String> {
    Optional<Course> findByCode(String code);
    boolean existsByCode(String code);
}
