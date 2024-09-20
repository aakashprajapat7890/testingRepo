package com.student.management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.management.entity.Student;
import com.student.management.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper; // This is to map objects to JSON and vice versa


    private Student student1;
    private Student student2;
    private  Student updateStudent;

    @BeforeEach
    void setUp() {
        student1 = new Student(1l, "jay", "1234", "jay@gamil.com", "harda","123");
        student2 = new Student(2L, "sanjay", "56789", "sanjay@gamil.com", "sirali","456");
         updateStudent=new Student(2L,"abhi","90676680","abhi@123","mumbai","890");
    }

    @Test
    void saveStudentTest()  throws Exception {
        when(studentService.createStudent(student1)).thenReturn(student1);
         mockMvc.perform(post("/student/create-student")
                         .content(objectMapper.writeValueAsString(student1))
                         .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());

    }


    @Test
    void getAllStudentTest() throws Exception {

        List<Student> students= Arrays.asList(student1,student2);
        when(studentService.getAllStudent()).thenReturn(students);
        mockMvc.perform(get("/student/getAllStudent")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void getByIdTest() throws Exception {
        when(studentService.findStudentById(2L)).thenReturn(student2);

        // Perform the GET request
        mockMvc.perform(get("/student/getStudentById/2")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void findByStudentNameTest() throws Exception {
        when(studentService.findStudentByName("jay")).thenReturn(student2);

        mockMvc.perform(get("/student/getByName/jay")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAllStudentTest() throws Exception {

        mockMvc.perform(delete("/student/deleteAllStudent")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void updateStudentTest() throws Exception {
      when(studentService.updateStudent(any(Student.class),eq(2L))).thenReturn(updateStudent);

      mockMvc.perform(put("/student/updateStudent/2")
              .contentType(MediaType.APPLICATION_JSON_VALUE)
              .content(objectMapper.writeValueAsString(updateStudent)))
              .andExpect(status().isOk());
    }
}