package com.example.proxyservice.InheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "TPC_mentor")
@Getter
@Setter
public class Mentor extends User{
    private int gradYear;
}
