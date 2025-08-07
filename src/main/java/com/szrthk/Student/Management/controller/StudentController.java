package com.szrthk.Student.Management.controller;


import com.szrthk.Student.Management.entity.Course;
import com.szrthk.Student.Management.entity.Student;
import com.szrthk.Student.Management.service.StudentService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {
    
    private final StudentService studentService;


   public StudentController(StudentService studentService){
       this.studentService = studentService;
   }
   @PostMapping
   public ResponseEntity<Student> create (@Valid @RequestBody Student student){
       return ResponseEntity.ok(studentService.create(student));
   }
   @GetMapping("/{id}")
   public Student get(@PathVariable String id){
       return studentService.getStudent(id);
   }
   @PutMapping("/{id}")
    public Student update (@PathVariable String id, @RequestBody Student student){
       return studentService.updateStudent(id, student);
   }
   @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
       studentService.delete(id);
       return ResponseEntity.noContent().build();
   }
   @PostMapping
    public ResponseEntity<Void> enroll(@PathVariable String studentId, @PathVariable String courseId){
       studentService.enroll(studentId, courseId);
       return ResponseEntity.ok().build();
   }
   @GetMapping("/{studentId}/courses")
    public List<Course> getCourses (@PathVariable String studentId){
       return studentService.getCourses(studentId);
   }




}
