package com.szrthk.Student.Management.repositery;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.szrthk.Student.Management.entity.Student;

public interface StudentRepo extends MongoRepository<Student, String> {

}
