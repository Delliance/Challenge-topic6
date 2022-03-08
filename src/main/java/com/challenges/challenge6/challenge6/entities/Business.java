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
public class Business { //TODO: change this to a one-to-one

        @Id
        @SequenceGenerator(
                name = "business_sequence",
                sequenceName = "business_sequence",
                allocationSize = 1
        )
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "business_sequence"
        )
        @Column(
                name = "business_id",
                updatable = false
        )
        private Long id;
        private String name;

}
