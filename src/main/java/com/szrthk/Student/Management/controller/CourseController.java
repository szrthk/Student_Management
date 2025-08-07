package com.szrthk.Student.Management.controller;

import com.szrthk.Student.Management.entity.Course;
import com.szrthk.Student.Management.repositery.CourseRepo;
import com.szrthk.Student.Management.repositery.ResourceNotFoundException;
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
    @GetMapping("/code/{code}")
    public ResponseEntity<Course> getByCode(@PathVariable String code) {
        Course course = courseRepo.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Course code not found"));
        return ResponseEntity.ok(course);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable String id) {
        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        return ResponseEntity.ok(course);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable String id, @RequestBody Course updatedCourse) {
        Course existing = courseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        existing.setTitle(updatedCourse.getTitle());
        existing.setCode(updatedCourse.getCode());

        Course saved = courseRepo.save(existing);
        return ResponseEntity.ok(saved);
    }
}
