package com.szrthk.Student.Management.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;

@Document(collection = "courses")
public class Course {

    @Id
    private String id;

    @NotBlank(message = "Course name is required")
    private String title;
}
