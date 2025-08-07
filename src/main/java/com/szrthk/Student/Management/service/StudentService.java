package com.szrthk.Student.Management.service;
import com.szrthk.Student.Management.entity.Course;
import com.szrthk.Student.Management.entity.Student;

import com.szrthk.Student.Management.repositery.CourseRepo;
import com.szrthk.Student.Management.repositery.ResourceNotFoundException;
import com.szrthk.Student.Management.repositery.StudentRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
@Service
public class StudentService {
    private StudentRepo studentRepo;
    private CourseRepo courseRepo;

    public StudentService(StudentRepo studentRepo, CourseRepo courseRepo){
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    public Student create(Student student){
        return studentRepo.save(student);
    }
    public Student getStudent(String id){
        return studentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }
    public Student updateStudent (String id, Student updated){
        Student existing = getStudent(id);
        existing.setName(updated.getName());
        existing.setAge(updated.getAge());
        existing.setEmail(updated.getEmail());
        return studentRepo.save(existing);
    }
    public void delete (String id){
        studentRepo.deleteById(id);
    }
    public void enroll (String studentId, String courseId){
        Student student =  getStudent(studentId);
        Course course =  courseRepo.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        student.getCourseIds().add(course.getId());
        studentRepo.save(student);
    }
    public List<Course> getCourses(String studentId) {
        Student student = getStudent(studentId);
        return courseRepo.findAllById(student.getCourseIds());
    }
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

}

