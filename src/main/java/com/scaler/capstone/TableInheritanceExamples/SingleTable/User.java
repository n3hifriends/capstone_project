package com.scaler.capstone.TableInheritanceExamples.SingleTable;

import jakarta.persistence.*;

import java.util.UUID;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.INTEGER)
@Entity(name = "st_user") // Table per class
public class User {
    @Id
    UUID id;
    String email;
}
