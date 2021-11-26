package com.orlando.smartworktask.domain;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty( value ="This is the first name of the user",required = true )
    private String firstName;
    @ApiModelProperty( value ="This is the last name of the user",required = true )
    private String lastName;


}
