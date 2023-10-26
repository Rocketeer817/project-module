package com.example.proxyservice.InheritanceExamples.InheritedTables;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Joined_TA")
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "user_id")
public class TA extends User {
    private int gradYear;
}
