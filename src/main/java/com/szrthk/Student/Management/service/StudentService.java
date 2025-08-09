package com.szrthk.Student.Management.service;

import com.szrthk.Student.Management.entity.Course;
import com.szrthk.Student.Management.entity.Student;
import com.szrthk.Student.Management.repositery.CourseRepo;
import com.szrthk.Student.Management.repositery.ResourceNotFoundException;
import com.szrthk.Student.Management.repositery.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;
    

    public StudentService(StudentRepo studentRepo, CourseRepo courseRepo){
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    public Student getStudentByName(String name) {
        Student student = studentRepo.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        populateCourses(student);
        return student;
    }

    // ✅ use rollNumber consistently from here
    public Student getStudent(String rollNumber){
        Student student = studentRepo.findByRollNumber(rollNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        populateCourses(student);
        return student;
    }

    public Student updateStudent(String rollNumber, Student updated){
        Student existing = getStudent(rollNumber);
        existing.setName(updated.getName());
        existing.setAge(updated.getAge());
        existing.setEmail(updated.getEmail());
        return studentRepo.save(existing);
    }

    public void delete(String rollNumber){
        if (!studentRepo.existsByRollNumber(rollNumber)) {
            throw new ResourceNotFoundException("Student not found");
        }
        studentRepo.deleteByRollNumber(rollNumber);
    }

    public void deleteAllStudents(){
        studentRepo.deleteAll();
    }

    public void enroll(String rollNumber, String courseId){
        Student student = getStudent(rollNumber);
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        student.getCourseIds().add(course.getId());
        studentRepo.save(student);
    }

    // New method to enroll using course code
    public void enrollByCourseCode(String rollNumber, String courseCode){
        Student student = getStudent(rollNumber);
        Course course = courseRepo.findByCode(courseCode)
                .orElseThrow(() -> new ResourceNotFoundException("Course with code " + courseCode + " not found"));
        student.getCourseIds().add(course.getId());
        studentRepo.save(student);
    }

    public List<Course> getCourses(String rollNumber) {
        Student student = getStudent(rollNumber);
        return courseRepo.findAllById(student.getCourseIds());
    }

    public List<Student> getAllStudents() {
        List<Student> students = studentRepo.findAll();
        students.forEach(this::populateCourses);
        return students;
    }

    private void populateCourses(Student student) {
        if (!student.getCourseIds().isEmpty()) {
            List<Course> courses = courseRepo.findAllById(student.getCourseIds());
            student.setCourses(courses);
        }
    }

    public Course updateCourse(String id, Course updated){
        Course existing = courseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        existing.setTitle(updated.getTitle());
        return courseRepo.save(existing);
    }

    public void deleteCourse(String id){
        courseRepo.deleteById(id);
    }

    public void deleteAllCourses(){
        courseRepo.deleteAll();
    }

    public Student create(Student student) {
        long count = studentRepo.count();
        String rollNumber = "STU" + String.format("%03d", count + 1);
        student.setRollNumber(rollNumber);
        return studentRepo.save(student);
    }
}