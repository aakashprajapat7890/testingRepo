package com.student.management.controller;

import com.student.management.entity.Student;
import com.student.management.exception.StudentNotFoundException;
import com.student.management.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @PostMapping("/create-student")
    public ResponseEntity<Student> saveStudent( @Valid @RequestBody Student student) {
        Student student1 = studentService.createStudent(student);
        return new ResponseEntity<>(student1, HttpStatus.CREATED);
    }

    @GetMapping("/getAllStudent")
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> allStudent = studentService.getAllStudent();
        return ResponseEntity.ok(allStudent);
    }

    @GetMapping("/getStudentById/{studentId}")
    public ResponseEntity<Student> getById(@PathVariable Long studentId) throws StudentNotFoundException {
        Student studentById = studentService.findStudentById(studentId);
     return  ResponseEntity.ok(studentById);
    }

    @GetMapping("getByName/{studentName}")
    public ResponseEntity<Student> findByStudentName(@PathVariable String studentName) {
        Student studentByName = studentService.findStudentByName(studentName);
        return ResponseEntity.ok(studentByName);
    }

    @DeleteMapping("deleteByName/{studentId}")
    public  String deleteByName(@PathVariable Long studentId)
    {
         studentService.deleteStudentId(studentId);
    return "student delete successfully";
    }

    @DeleteMapping("/deleteAllStudent")
    public  String deleteAllStudent()
    {
        studentService.deleteAllStudent();
        return "All student delete sucessfully";
    }
    @PutMapping("updateStudent/{studentId}")
    public  ResponseEntity<Student> updateStudent(@RequestBody Student student,@PathVariable Long studentId)
    {
        Student student1 = studentService.updateStudent(student, studentId);
        return ResponseEntity.ok(student1);
    }


}
