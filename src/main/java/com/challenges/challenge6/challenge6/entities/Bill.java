package com.challenges.challenge6.challenge6.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "Bill")
@Table(
        name = "tbl_bill",
        uniqueConstraints = {
            @UniqueConstraint(
                    name = "unique_id",columnNames = "bill_id"
            )
        }
)
public class Bill {

    @Id
    @Column(
            name = "bill_id",
            updatable = false
    )
    private long id;
    private LocalDateTime dueDate;
    private long toPay;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "business_id",
            referencedColumnName = "business_id"
    )
    private Business business;

}
