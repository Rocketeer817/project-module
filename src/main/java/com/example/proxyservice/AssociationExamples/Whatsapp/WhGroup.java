package com.example.proxyservice.AssociationExamples.Whatsapp;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "Whatsapp_Group")
public class WhGroup extends BaseClass{
    private String name;
    private String description;
    private String type;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Whatsapp_Group_Members", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "member_id"))
    private List<Member> membersList;


}
