package com.scaler.capstone.TableInheritanceExamples.JoinedClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "jc_mentor")
@PrimaryKeyJoinColumn(name = "user_id")
public class Mentor extends User {
    Double rating;
}
