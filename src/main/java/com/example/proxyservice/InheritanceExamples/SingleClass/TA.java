package com.example.proxyservice.InheritanceExamples.SingleClass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "SC_TA")
@Getter
@Setter
@DiscriminatorValue(value = "1")
public class TA extends User {
    private int gradYear;
}
