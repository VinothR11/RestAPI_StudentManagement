package com.example.crud.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Entity
@Table(name = "student")
@Data
public class Student {
    @Id
    @GeneratedValue(generator="sch1.student_id_seq")
    @SequenceGenerator(name="sch1.student_id_seq",sequenceName="sch1.student_id_seq", allocationSize=1)
    private long id;
    private String name;
    private LocalDate dob;
    private String gender;
    private String dept;

}
