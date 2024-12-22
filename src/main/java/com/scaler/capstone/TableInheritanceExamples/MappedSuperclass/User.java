package com.scaler.capstone.TableInheritanceExamples.MappedSuperclass;

import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "msc_user") // Table per class
//@MappedSuperclass
public abstract class User {
    @Id
    UUID id;
    String email;
}
