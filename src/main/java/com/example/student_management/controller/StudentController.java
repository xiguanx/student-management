package com.example.student_management.controller;

import com.example.student_management.dto.StudentDto;
import com.example.student_management.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // CREATE
    // POST /api/students
    @PostMapping
    public ResponseEntity<StudentDto> createStudent(
            @Valid @RequestBody StudentDto studentDto) {

        StudentDto savedStudent =
                studentService.createStudent(studentDto);

        return new ResponseEntity<>(
                savedStudent,
                HttpStatus.CREATED
        );
    }

    // GET BY ID
    // GET /api/students/{id}
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(
            @PathVariable Long id) {

        StudentDto student =
                studentService.getStudentById(id);

        return ResponseEntity.ok(student);
    }

    // GET ALL
    // GET /api/students
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {

        List<StudentDto> students =
                studentService.getAllStudents();

        return ResponseEntity.ok(students);
    }

    // UPDATE
    // PUT /api/students/{id}
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentDto studentDto) {

        StudentDto updatedStudent =
                studentService.updateStudent(id, studentDto);

        return ResponseEntity.ok(updatedStudent);
    }

    // DELETE
    // DELETE /api/students/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }
}