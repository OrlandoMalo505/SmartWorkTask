package com.orlando.smartworktask.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "name_id")
    private Name name;

    @ApiModelProperty( value ="This is the type of the phone book(Work, Home or Cellphone)",required = true )
    private Type type;

    @ApiModelProperty( value ="This is the number of the phone book.",required = true )
    private Long number;
}
