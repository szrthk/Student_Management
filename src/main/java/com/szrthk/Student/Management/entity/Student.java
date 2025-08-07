package com.szrthk.Student.Management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import jakarta.validation.constraints.*;
import java.util.Set;
import java.util.List;
import com.szrthk.Student.Management.entity.Course;

@Data
@Document(collection = "students")
public class Student {

    @Id
    private String id;
    
    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email address")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    private int age;

    @JsonIgnore
    private Set<String> courseIds = new HashSet<>();
    private String rollNumber;
    
    @Transient
    private List<Course> courses;
}
