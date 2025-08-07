package com.szrthk.Student.Management.controller;

import com.szrthk.Student.Management.entity.Course;
import com.szrthk.Student.Management.repositery.CourseRepo;
import com.szrthk.Student.Management.repositery.ResourceNotFoundException;
import com.szrthk.Student.Management.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseRepo courseRepo;
    private final StudentService studentService;

    public CourseController(CourseRepo courseRepo, StudentService studentService) {
        this.courseRepo = courseRepo;
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Course> create(@Valid @RequestBody Course course) {
        if (courseRepo.existsByCode(course.getCode())) {
            throw new ResourceNotFoundException("Course code already exists");
        }
        return ResponseEntity.ok(courseRepo.save(course));
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseRepo.findAll());
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
        return ResponseEntity.ok(courseRepo.save(existing));
    }

    @PutMapping("/code/{code}")
    public ResponseEntity<Course> updateCourseByCode(@PathVariable String code, @Valid @RequestBody Course updatedCourse) {
        Course existing = courseRepo.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Course with code " + code + " not found"));
        
        // Check if the new code already exists (if it's different from the current code)
        if (!code.equals(updatedCourse.getCode()) && courseRepo.existsByCode(updatedCourse.getCode())) {
            throw new ResourceNotFoundException("Course code " + updatedCourse.getCode() + " already exists");
        }
        
        existing.setTitle(updatedCourse.getTitle());
        existing.setCode(updatedCourse.getCode());
        
        Course saved = courseRepo.save(existing);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String id) {
        courseRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/code/{code}")
    public ResponseEntity<Void> deleteByCode(@PathVariable String code) {
        Course course = courseRepo.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        courseRepo.delete(course);
        return ResponseEntity.noContent().build();
    }

    // Delete all courses
    @DeleteMapping
    public ResponseEntity<Void> deleteAllCourses(){
        studentService.deleteAllCourses();
        return ResponseEntity.noContent().build();
    }
}