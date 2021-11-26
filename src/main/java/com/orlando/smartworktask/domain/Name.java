package com.orlando.smartworktask.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Name {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nameId;
    private String firstName;
    private String lastName;


}
