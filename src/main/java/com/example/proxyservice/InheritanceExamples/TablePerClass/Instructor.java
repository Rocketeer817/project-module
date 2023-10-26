package com.example.proxyservice.InheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "TPC_instructor")
public class Instructor extends User{
    private String company;
}
