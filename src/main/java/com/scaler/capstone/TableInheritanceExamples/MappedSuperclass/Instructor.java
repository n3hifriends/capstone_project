package com.scaler.capstone.TableInheritanceExamples.MappedSuperclass;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;

@Entity(name = "msc_instructor")
public class Instructor extends User {
    String company;
}
