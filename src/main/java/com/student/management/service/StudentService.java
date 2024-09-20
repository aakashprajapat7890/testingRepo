package com.student.management.service;

import com.student.management.entity.Student;
import com.student.management.exception.StudentNotFoundException;
import com.student.management.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;



    public Student createStudent(Student student) {
      //  student.setStudentPass(passwordEncoder.encode(student.getStudentPass()));
        return studentRepo.save(student);
    }
    public List<Student> getAllStudent() {
        List<Student> all = studentRepo.findAll();
        return all;
    }
    public Student findStudentByName(String studentName) {
        return studentRepo.findByStudentName(studentName);
    }

    public  Student findStudentById(Long studentId) throws StudentNotFoundException {
        return studentRepo.findById(studentId)
                 .orElseThrow(StudentNotFoundException::new);

    }

    public void deleteAllStudent() {
        studentRepo.deleteAll();
    }

    public void deleteStudentId(Long studentId)
    {
         studentRepo.deleteById(studentId);
    }

    public Student updateStudent(Student student,Long studentId)
    {
        Student existingStudent = studentRepo.findById(studentId).orElse(null);
        if(existingStudent!=null)
        {
            existingStudent.setStudentName(student.getStudentName());
            existingStudent.setStudentMob(student.getStudentMob());
            existingStudent.setStudentEmail(student.getStudentEmail());
            existingStudent.setStudentAdd(student.getStudentAdd());
            return studentRepo.save(existingStudent);
        }
        return null;
    }


}
