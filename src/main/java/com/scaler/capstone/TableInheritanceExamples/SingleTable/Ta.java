package com.scaler.capstone.TableInheritanceExamples.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "st_ta")
@DiscriminatorValue(value = "10")
public class Ta extends User {
    int helpRequests;
}
