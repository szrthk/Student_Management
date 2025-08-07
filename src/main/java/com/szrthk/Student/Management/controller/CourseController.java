package com.szrthk.Student.Management.controller;

import com.szrthk.Student.Management.entity.Course;
import com.szrthk.Student.Management.repositery.CourseRepo;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseRepo courseRepo;

    public CourseController(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @PostMapping
    public ResponseEntity<Course> create(@Valid @RequestBody Course course) {
        return ResponseEntity.ok(courseRepo.save(course));
    }
}
