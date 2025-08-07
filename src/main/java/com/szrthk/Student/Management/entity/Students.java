package com.szrthk.Student.Management.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;

import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.*;
import java.util.Set;


@Document(collection = "students")
public class Students {

    @Id
    private String id;
    
    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email address")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    private int age;

    @SuppressWarnings("unused")
    private Set<String> courseIds = new HashSet<>();

}
