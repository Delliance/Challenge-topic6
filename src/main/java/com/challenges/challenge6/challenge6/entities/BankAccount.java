package com.challenges.challenge6.challenge6.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "Account")
@Table(
        name = "tbl_account"
)
public class BankAccount {

    @Id
    @SequenceGenerator(
            name = "account_sequence",
            sequenceName = "account_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_sequence"
    )
    @Column(
            name = "account_id",
            updatable = false
    )
    private long id;
    private double balance;


    @Enumerated(EnumType.STRING)
    private BankAccountType bankAccountType;

    @Embedded
    private Owner owner;

    @ManyToOne(
        cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "bank_id",
            referencedColumnName = "bank_id",
            nullable = false
    )
    private Bank bank;




}
