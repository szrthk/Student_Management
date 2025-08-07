package com.szrthk.Student.Management.controller;

import com.szrthk.Student.Management.entity.Course;
import com.szrthk.Student.Management.repositery.CourseRepo;
import com.szrthk.Student.Management.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseRepo courseRepo;
    @Autowired
    public StudentService studentService;

    public CourseController(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @PostMapping
    public ResponseEntity<Course> create(@Valid @RequestBody Course course) {
        return ResponseEntity.ok(courseRepo.save(course));
    }
    @GetMapping
    public ResponseEntity<List<Course>> getallcourses(){
        List<Course> courses = courseRepo.findAll();
        return ResponseEntity.ok(courses);
    }
}
