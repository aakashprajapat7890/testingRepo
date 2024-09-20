package com.student.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "STUDENT_INFO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long studentId;

    @NotNull(message = "student name not be null")
    private String studentName;
    @Size(max = 10)
    private String studentMob;
    @Email
    private String studentEmail;
    @NotNull
    private String studentAdd;
    @Size(min = 6,max = 12)
    private String studentPass;
    
    
	
    
    

}
