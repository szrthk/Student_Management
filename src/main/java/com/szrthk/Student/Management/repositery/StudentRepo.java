package com.szrthk.Student.Management.repositery;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.szrthk.Student.Management.entity.Students;

public interface StudentRepo extends MongoRepository<Students, String> {

}
