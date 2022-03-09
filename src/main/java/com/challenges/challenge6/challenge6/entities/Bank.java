package com.challenges.challenge6.challenge6.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bank {

    @Id
    @SequenceGenerator(
            name = "bank_sequence",
            sequenceName = "bank_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bank_sequence"
    )
    @Column(
            name = "bank_id",
            updatable = false
    )
    private long id;

    @Column(
            name = "bank_name",
            updatable = false,
            nullable = false
    )
    private String name;

}
