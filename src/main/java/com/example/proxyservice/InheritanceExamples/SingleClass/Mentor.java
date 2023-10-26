package com.example.proxyservice.InheritanceExamples.SingleClass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "SC_mentor")
@Getter
@Setter
@DiscriminatorValue(value = "2")
public class Mentor extends User {
    private int gradYear;
}
