# Spring Boot Development Notes

## Spring Data Repository Pattern

### Key Learning: Repository-First Development
When adding new query methods in Spring Boot applications, always follow this order:

1. **Repository Interface First** - Define the method signature in your repository interface
2. **Service Layer Second** - Then use that method in your service
3. **Controller Layer Third** - Finally expose it through your controller

### Example Issue Solved
**Problem**: `The method findByName(String) is undefined for the type StudentRepo`

**Root Cause**: The `StudentService.getStudentByName()` method was trying to call `studentRepo.findByName(name)`, but this method wasn't declared in the `StudentRepo` interface.

**Solution**: Added the missing method declaration to `StudentRepo.java`:
```java
Optional<Student> findByName(String name);
```

### Spring Data Query Method Patterns

Spring Data automatically implements repository methods based on naming conventions:

#### Basic Patterns
```java
// Exact match
Optional<Student> findByName(String name);
Optional<Student> findByEmail(String email);
Optional<Student> findByRollNumber(String rollNumber);

// Multiple results
List<Student> findByAge(int age);
```

#### Advanced Patterns
```java
// Partial match
List<Student> findByNameContaining(String namepart);

// Case insensitive
List<Student> findByNameIgnoreCase(String name);

// Multiple conditions
List<Student> findByNameAndAge(String name, int age);

// Comparison operators
List<Student> findByAgeGreaterThan(int age);
List<Student> findByAgeBetween(int minAge, int maxAge);
```

### How Spring Data Works
- **Method Naming**: `findBy` + `FieldName` tells Spring Data which field to query
- **Return Types**: 
  - `Optional<Student>` for single results that might not exist
  - `List<Student>` for multiple results
- **Automatic Implementation**: Spring Data generates the actual query implementation at runtime

### Project Structure Reference
```
Student-Management/
├── src/main/java/com/szrthk/Student/Management/
│   ├── entity/
│   │   ├── Student.java          # Entity with @Document annotation
│   │   └── Course.java
│   ├── repositery/               # Note: typo in folder name (repository)
│   │   ├── StudentRepo.java      # Repository interface
│   │   ├── CourseRepo.java
│   │   └── ResourceNotFoundException.java
│   ├── service/
│   │   └── StudentService.java   # Business logic layer
│   └── controller/
│       ├── StudentController.java
│       └── CourseController.java
```

### Current Student Entity Fields
- `id` (String) - MongoDB document ID
- `name` (String) - Student name with validation
- `email` (String) - Email with validation
- `age` (int) - Age with minimum 18 validation
- `rollNumber` (String) - Unique student identifier
- `courseIds` (Set<String>) - Referenced course IDs
- `courses` (List<Course>) - Transient field populated by service

### Repository Methods Currently Available
```java
// StudentRepo methods:
Optional<Student> findByRollNumber(String rollNumber);
Optional<Student> findByName(String name);           // ✅ Added to fix error
void deleteByRollNumber(String rollNumber);
boolean existsByRollNumber(String rollNumber);
```

### Best Practices Learned
1. Always define repository methods before using them in services
2. Use `Optional<>` for single results that might not exist
3. Use descriptive method names following Spring Data conventions
4. Validate entities using annotations (`@NotBlank`, `@Email`, `@Min`)
5. Use `@Transient` for fields that shouldn't be persisted to database
