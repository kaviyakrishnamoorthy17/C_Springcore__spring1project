package com.tnsif.placement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    // ✅ Constructor injection (recommended instead of @Autowired field)
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // ✅ Add a new student (CREATE)
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    // ✅ Get all students (READ)
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // ✅ Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    // ✅ Update student by ID
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student existing = studentService.getStudentById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        // Update only non-null fields
        existing.setName(student.getName() != null ? student.getName() : existing.getName());
        existing.setEmail(student.getEmail() != null ? student.getEmail() : existing.getEmail());
        existing.setCourse(student.getCourse() != null ? student.getCourse() : existing.getCourse());
        existing.setAge(student.getAge() != null ? student.getAge() : existing.getAge());
        existing.setDepartment(student.getDepartment() != null ? student.getDepartment() : existing.getDepartment());
        existing.setPhone(student.getPhone() != null ? student.getPhone() : existing.getPhone());
        existing.setAddress(student.getAddress() != null ? student.getAddress() : existing.getAddress());

        return ResponseEntity.ok(studentService.saveStudent(existing));
    }

    // ✅ Delete student by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        Student existing = studentService.getStudentById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}