package com.scaler.capstone.TableInheritanceExamples.MappedSuperclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;

@Entity(name = "msc_mentor")
public class Mentor extends User {
    Double rating;
}
