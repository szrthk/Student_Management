package com.szrthk.Student.Management.controller;


import com.szrthk.Student.Management.entity.Course;
import com.szrthk.Student.Management.entity.Student;
import com.szrthk.Student.Management.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {
    
    private  StudentService studentService;


   public StudentController(StudentService studentService){
       this.studentService = studentService;
   }
   @PostMapping
   public ResponseEntity<Student> create (@Valid @RequestBody Student student){
       return ResponseEntity.ok(studentService.create(student));
   }
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
   @GetMapping("/{rollNumber}")
   public Student get(@PathVariable String rollNumber){
       return studentService.getStudent(rollNumber);
   }
   @PutMapping("/{rollNumber}")
    public Student update (@PathVariable String rollNumber, @RequestBody Student student){
       return studentService.updateStudent(rollNumber, student);
   }
   @DeleteMapping("/{rollNumber}")
    public ResponseEntity<Void> delete(@PathVariable String rollNumber){
       studentService.delete(rollNumber);
       return ResponseEntity.noContent().build();
   }
   
   // Delete all students
   @DeleteMapping
   public ResponseEntity<Void> deleteAllStudents(){
       studentService.deleteAllStudents();
       return ResponseEntity.noContent().build();
   }
   
   @PostMapping("/{rollNumber}/courses/{courseId}")
    public ResponseEntity<Void> enroll(@PathVariable String rollNumber, @PathVariable String courseId){
       studentService.enroll(rollNumber, courseId);
       return ResponseEntity.ok().build();
   }

   // New endpoint to enroll using course code
   @PostMapping("/{rollNumber}/courses/code/{courseCode}")
    public ResponseEntity<Void> enrollByCourseCode(@PathVariable String rollNumber, @PathVariable String courseCode){
       studentService.enrollByCourseCode(rollNumber, courseCode);
       return ResponseEntity.ok().build();
   }

   @GetMapping("/{rollNumber}/courses")
    public List<Course> getCourses (@PathVariable String rollNumber){
       return studentService.getCourses(rollNumber);
   }





}
