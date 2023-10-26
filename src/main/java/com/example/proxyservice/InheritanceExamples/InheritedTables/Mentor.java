package com.example.proxyservice.InheritanceExamples.InheritedTables;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Joined_mentor")
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "user_id")
public class Mentor extends User {
    private int gradYear;
}
