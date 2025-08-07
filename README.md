# Student Management System

A Spring Boot application for managing students and courses with MongoDB as the database.

## 🚀 Features

- **Student Management**: Create, read, update, and delete students
- **Course Management**: Create, read, update, and delete courses
- **Enrollment System**: Enroll students in courses using course ID or code
- **Course Display**: View student details with enrolled course information
- **Bulk Operations**: Delete all students or courses at once

## 🛠️ Technology Stack

- **Backend**: Spring Boot 3.x
- **Database**: MongoDB
- **Language**: Java 17+
- **Build Tool**: Maven
- **Documentation**: Spring Boot Actuator

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- MongoDB (local or cloud instance)

## 🚀 Quick Start

### 1. Clone the Repository
```bash
git clone <your-repository-url>
cd Student-Management
```

### 2. Configure MongoDB
Make sure MongoDB is running on `localhost:27017` or update the connection string in `application.properties`.

### 3. Run the Application
```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## 📚 API Documentation

### Student Endpoints

#### Create Student
```http
POST /students
Content-Type: application/json

{
    "name": "John Doe",
    "email": "john@example.com",
    "age": 20
}
```

#### Get All Students
```http
GET /students
```

#### Get Student by Roll Number
```http
GET /students/{rollNumber}
```

#### Update Student
```http
PUT /students/{rollNumber}
Content-Type: application/json

{
    "name": "John Updated",
    "email": "john.updated@example.com",
    "age": 21
}
```

#### Delete Student
```http
DELETE /students/{rollNumber}
```

#### Delete All Students
```http
DELETE /students
```

#### Enroll Student in Course (by ID)
```http
POST /students/{rollNumber}/courses/{courseId}
```

#### Enroll Student in Course (by Code)
```http
POST /students/{rollNumber}/courses/code/{courseCode}
```

#### Get Student's Courses
```http
GET /students/{rollNumber}/courses
```

### Course Endpoints

#### Create Course
```http
POST /courses
Content-Type: application/json

{
    "title": "Computer Science",
    "code": "CS101"
}
```

#### Get All Courses
```http
GET /courses
```

#### Get Course by ID
```http
GET /courses/{id}
```

#### Get Course by Code
```http
GET /courses/code/{code}
```

#### Update Course by ID
```http
PUT /courses/{id}
Content-Type: application/json

{
    "title": "Advanced Computer Science",
    "code": "CS101"
}
```

#### Update Course by Code
```http
PUT /courses/code/{code}
Content-Type: application/json

{
    "title": "Computer Science Fundamentals",
    "code": "CS102"
}
```

#### Delete Course by ID
```http
DELETE /courses/{id}
```

#### Delete Course by Code
```http
DELETE /courses/code/{code}
```

#### Delete All Courses
```http
DELETE /courses
```

## 📊 Data Models

### Student Entity
```json
{
    "id": "507f1f77bcf86cd799439011",
    "name": "John Doe",
    "email": "john@example.com",
    "age": 20,
    "rollNumber": "STU001",
    "courses": [
        {
            "title": "Computer Science",
            "code": "CS101"
        }
    ]
}
```

### Course Entity
```json
{
    "id": "507f1f77bcf86cd799439012",
    "title": "Computer Science",
    "code": "CS101"
}
```

## 🔧 Configuration

### Application Properties
```properties
# MongoDB Configuration
spring.data.mongodb.uri=mongodb://localhost:27017/student-management

# Server Configuration
server.port=8080

# Logging
logging.level.com.szrthk.Student.Management=DEBUG
```

## 🧪 Testing

### Using curl

#### Create a Course
```bash
curl -X POST http://localhost:8080/courses \
  -H "Content-Type: application/json" \
  -d '{"title": "Computer Science", "code": "CS101"}'
```

#### Create a Student
```bash
curl -X POST http://localhost:8080/students \
  -H "Content-Type: application/json" \
  -d '{"name": "John Doe", "email": "john@example.com", "age": 20}'
```

#### Enroll Student in Course
```bash
curl -X POST http://localhost:8080/students/STU001/courses/code/CS101
```

#### Get All Students
```bash
curl -X GET http://localhost:8080/students
```

## 📁 Project Structure

```
src/main/java/com/szrthk/Student/Management/
├── controller/
│   ├── CourseController.java
│   ├── StudentController.java
│   └── healthcheck.java
├── entity/
│   ├── Course.java
│   └── Student.java
├── repositery/
│   ├── CourseRepo.java
│   ├── StudentRepo.java
│   └── ResourceNotFoundException.java
├── service/
│   └── StudentService.java
└── StudentManagementApplication.java
```

## 🔍 Key Features Explained

### 1. Auto-generated Roll Numbers
Students get automatic roll numbers in format `STU001`, `STU002`, etc.

### 2. Course Code Support
- Create courses with human-readable codes (e.g., "CS101", "MATH101")
- Enroll students using course codes instead of IDs
- Update and delete courses by code

### 3. Clean JSON Responses
- Course IDs are hidden from responses
- Only course titles and codes are displayed
- Student course IDs are hidden but functional

### 4. Validation
- Email validation for students
- Age validation (minimum 18)
- Required field validation
- Duplicate course code prevention

## 🚨 Error Handling

The application includes comprehensive error handling:

- **ResourceNotFoundException**: When student/course not found
- **Validation Errors**: For invalid input data
- **Duplicate Code Errors**: When course code already exists

## 🔒 Security Considerations

- Input validation on all endpoints
- Proper error messages without exposing internal details
- MongoDB injection protection through Spring Data

## 🚀 Deployment

### Local Development
```bash
./mvnw spring-boot:run
```

### Production Build
```bash
./mvnw clean package
java -jar target/Student-Management-0.0.1-SNAPSHOT.jar
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📝 License

This project is licensed under the MIT License.

## 📞 Support

For questions or issues, please create an issue in the repository.

---

**Happy Coding! 🎉**
