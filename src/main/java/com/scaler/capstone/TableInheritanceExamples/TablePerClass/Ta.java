package com.scaler.capstone.TableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name = "tpc_ta")
public class Ta extends User {
    int helpRequests;
}
