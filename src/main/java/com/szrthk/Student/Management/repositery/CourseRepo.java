package com.szrthk.Student.Management.repositery;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.szrthk.Student.Management.entity.Course;

public interface CourseRepo extends MongoRepository<Course, String> {

}
