package com.scaler.capstone.TableInheritanceExamples.SingleTable;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "st_instructor")
@DiscriminatorValue(value = "30")
public class Instructor extends User {
    String company;
}
