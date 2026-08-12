package com.example.student_management.service;

import com.example.student_management.dto.StudentDto;
import com.example.student_management.entity.Student;
import com.example.student_management.mapper.StudentMapper;
import com.example.student_management.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // POST - Create Student
    public StudentDto createStudent(StudentDto studentDto) {

        Student student = StudentMapper.mapToStudent(studentDto);

        Student savedStudent = studentRepository.save(student);

        return StudentMapper.mapToStudentDto(savedStudent);
    }

    // GET - Student by ID
    public StudentDto getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with id: " + id)
                );

        return StudentMapper.mapToStudentDto(student);
    }

    // GET - All Students
    public List<StudentDto> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(StudentMapper::mapToStudentDto)
                .toList();
    }

    // PUT - Update Student
    public StudentDto updateStudent(Long id, StudentDto studentDto) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with id: " + id)
                );

        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());

        Student updatedStudent = studentRepository.save(student);

        return StudentMapper.mapToStudentDto(updatedStudent);
    }

    // Delete
    public void deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student not found with id: " + id
                        )
                );

        studentRepository.delete(student);
    }
}
