# Student Management System

A modern REST API built with Spring Boot for managing students and courses in an educational institution.

## 📖 Overview

This application provides a complete CRUD (Create, Read, Update, Delete) system for managing:
- **Students** with auto-generated roll numbers
- **Courses** with unique course codes
- **Enrollment** system linking students to courses

## 🚀 Features

- ✅ Student management with validation
- ✅ Course management with unique codes
- ✅ Student enrollment in courses
- ✅ Auto-generated roll numbers (STU001, STU002, etc.)
- ✅ Find students by name or roll number
- ✅ Find courses by ID or code
- ✅ Bulk delete operations
- ✅ Data validation and error handling

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3.5.4**
- **MongoDB** (NoSQL Database)
- **Maven** (Build Tool)
- **Lombok** (Code Generation)

## 🏃‍♂️ Quick Start

### Prerequisites
- Java 17+
- MongoDB running on localhost:27017
- Maven 3.6+

### Run the Application
```bash
# Clone and navigate to project
git clone <repository-url>
cd Student-Management

# Run with Maven
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

## 📋 API Endpoints

### 👨‍🎓 Student Operations

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/students` | Create a new student |
| `GET` | `/students` | Get all students |
| `GET` | `/students/{rollNumber}` | Get student by roll number |
| `GET` | `/students/name/{name}` | Get student by name |
| `PUT` | `/students/{rollNumber}` | Update student |
| `DELETE` | `/students/{rollNumber}` | Delete student |
| `DELETE` | `/students` | Delete all students |

### 🎓 Course Operations

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/courses` | Create a new course |
| `GET` | `/courses` | Get all courses |
| `GET` | `/courses/{id}` | Get course by ID |
| `GET` | `/courses/code/{code}` | Get course by code |
| `PUT` | `/courses/{id}` | Update course by ID |
| `PUT` | `/courses/code/{code}` | Update course by code |
| `DELETE` | `/courses/{id}` | Delete course by ID |
| `DELETE` | `/courses/code/{code}` | Delete course by code |
| `DELETE` | `/courses` | Delete all courses |

### 📚 Enrollment Operations

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/students/{rollNumber}/courses/{courseId}` | Enroll student by course ID |
| `POST` | `/students/{rollNumber}/courses/code/{courseCode}` | Enroll student by course code |
| `GET` | `/students/{rollNumber}/courses` | Get student's enrolled courses |

## 💡 Usage Examples

### Create a Student
```bash
curl -X POST http://localhost:8080/students \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "age": 20
  }'
```

**Response:**
```json
{
  "id": "507f1f77bcf86cd799439011",
  "name": "John Doe",
  "email": "john@example.com",
  "age": 20,
  "rollNumber": "STU001",
  "courses": []
}
```

### Create a Course
```bash
curl -X POST http://localhost:8080/courses \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Computer Science Fundamentals",
    "code": "CS101"
  }'
```

**Response:**
```json
{
  "title": "Computer Science Fundamentals",
  "code": "CS101"
}
```

### Enroll Student in Course
```bash
curl -X POST http://localhost:8080/students/STU001/courses/code/CS101
```

### Get All Students
```bash
curl -X GET http://localhost:8080/students
```

**Response:**
```json
[
  {
    "id": "507f1f77bcf86cd799439011",
    "name": "John Doe",
    "email": "john@example.com",
    "age": 20,
    "rollNumber": "STU001",
    "courses": [
      {
        "title": "Computer Science Fundamentals",
        "code": "CS101"
      }
    ]
  }
]
```

## 📊 Data Models

### Student
```json
{
  "id": "string",
  "name": "string (required)",
  "email": "string (valid email required)",
  "age": "number (minimum 18)",
  "rollNumber": "string (auto-generated)",
  "courses": "array (enrolled courses)"
}
```

### Course
```json
{
  "title": "string (required)",
  "code": "string (required, unique)"
}
```

## ✅ Validation Rules

### Student Validation
- **Name**: Required, cannot be blank
- **Email**: Must be valid email format
- **Age**: Minimum 18 years old

### Course Validation
- **Title**: Required, cannot be blank
- **Code**: Required, must be unique across all courses

## 🔧 Configuration

Default configuration in `application.properties`:
```properties
# MongoDB connection
spring.data.mongodb.uri=mongodb://localhost:27017/student-management

# Server port
server.port=8080
```

## 🚨 Error Handling

The API returns appropriate HTTP status codes and error messages:

- `400 Bad Request` - Validation errors
- `404 Not Found` - Resource not found
- `409 Conflict` - Duplicate course code
- `500 Internal Server Error` - Server errors

Example error response:
```json
{
  "message": "Course code already exists"
}
```

## 🎯 Key Features Explained

### Auto-Generated Roll Numbers
Students automatically receive roll numbers in the format `STU001`, `STU002`, etc., based on the total count of students.

### Flexible Course Enrollment
Students can be enrolled in courses using either:
- Course ID (internal database ID)
- Course Code (human-readable code like "CS101")

### Clean API Responses
- Course internal IDs are hidden from responses
- Students see course titles and codes, not database IDs
- Enrolled courses are populated automatically when fetching students

## 🏗️ Project Structure

```
src/main/java/com/szrthk/Student/Management/
├── controller/          # REST endpoints
│   ├── StudentController.java
│   └── CourseController.java
├── entity/             # Data models
│   ├── Student.java
│   └── Course.java
├── repositery/         # Database layer
│   ├── StudentRepo.java
│   ├── CourseRepo.java
│   └── ResourceNotFoundException.java
├── service/           # Business logic
│   └── StudentService.java
└── StudentManagementApplication.java
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test your changes
5. Submit a pull request

## 📝 License

This project is open source and available under the MIT License.

---

**Built by @szrthk with ❤️ using Spring Boot**