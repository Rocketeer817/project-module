package com.example.proxyservice.InheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "TPC_TA")
@Getter
@Setter
public class TA extends  User {
    private int gradYear;
}
