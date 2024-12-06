package com.example.crud.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "student")
@Data
public class Student {

    @Id
    @GeneratedValue(generator = "sch1.student_id_seq")
    @SequenceGenerator(name = "sch1.student_id_seq", sequenceName = "sch1.student_id_seq", allocationSize = 1)
    private long id;

    private String name;

    private LocalDate dob;

    private String gender;

    private String dept;

    // Constructors, getters, setters (lombok @Data takes care of this)
    // No need to manually write getters and setters due to Lombok @Data

    public Student() {
    }

    public Student(long id, String name, LocalDate dob, String gender, String dept) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.dept = dept;
    }
}
