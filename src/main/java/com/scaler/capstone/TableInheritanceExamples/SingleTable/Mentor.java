package com.scaler.capstone.TableInheritanceExamples.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "st_mentor")
@DiscriminatorValue(value = "20")
public class Mentor extends User {
    Double rating;
}
