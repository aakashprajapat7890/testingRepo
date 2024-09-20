package com.student.management.service;

import com.student.management.entity.Student;
import com.student.management.repository.StudentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepo studentRepo;

    private Student student;
private  Student updatedStudent;
    private List<Student> studentList ;

    @BeforeEach
    void setUp() {
        student=new Student(1L, "John", "123456789", "john@example.com", "123 Street","123");
        studentList=Arrays.asList(
                new Student(1L, "John", "123456789", "john@example.com", "123 Street","234"),
                new Student(2L, "John", "123456789", "john@example.com", "123 Street","789"));
        updatedStudent=new Student(1L, "yash", "56789", "yash@example.com", "127 Street indore","890");

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveMethodTest() {
//        Student student = new Student(1L, "John", "123456789", "john@example.com", "123 Street");
        when(studentRepo.save(student)).thenReturn(student);
        Student createdStudent = studentService.createStudent(student);
        assertEquals("John", createdStudent.getStudentName());
    }

    @Test
    public  void getAllStudentTest()
    {
//        Student student1 = new Student(1L, "John", "123456789", "john@example.com", "123 Street");
//        Student student2 = new Student(2L, "John", "123456789", "john@example.com", "123 Street");
//    List<Student> students= Arrays.asList(student1,student2);

    when(studentRepo.findAll()).thenReturn(studentList);
    List<Student> result=studentService.getAllStudent();
    assertEquals(2,studentList.size());
    }

    @Test
    public  void getStudentBYNameTest()
    {
       when(studentRepo.findByStudentName("John")).thenReturn(student);
       Student resultStudent=studentService.findStudentByName("John");
       assertEquals("John",resultStudent.getStudentName());
    }

    @Test
    public void deleteStudentTest()
    {
        doNothing().when(studentRepo).deleteById(1L);
        studentService.deleteStudentId(1L);
        verify(studentRepo,times(1)).deleteById(1L);
    }

    @Test
    public void  updateStudentTest()
    {
        when(studentRepo.findById(1l)).thenReturn(Optional.of(student));
        when(studentRepo.save(student)).thenReturn(updatedStudent);
         Student result=studentService.updateStudent(updatedStudent,1l);
         assertEquals(1l,result.getStudentId());
    }

}
