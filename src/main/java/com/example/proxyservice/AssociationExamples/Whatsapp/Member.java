package com.example.proxyservice.AssociationExamples.Whatsapp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "Whatsapp_Member")
public class Member extends BaseClass{
    private String name;
    private String age;
    private String email;
    private String phone;
    @ManyToMany(mappedBy = "membersList")
    private List<WhGroup> groupsList;
}
