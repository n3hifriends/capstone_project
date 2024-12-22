package com.scaler.capstone.TableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name = "tpc_mentor")
public class Mentor extends  User{
    Double rating;
}
