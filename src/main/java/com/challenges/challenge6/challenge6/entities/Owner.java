package com.challenges.challenge6.challenge6.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@AttributeOverrides({
        @AttributeOverride(
                name = "firstName",
                column = @Column(name = "owner_first_name")
        ),
        @AttributeOverride(
                name = "lastName",
                column = @Column(name = "owner_last_name")
        ),
        @AttributeOverride(
                name = "dni",
                column = @Column(name = "owner_dni")
        )
})
@Table(
    uniqueConstraints = {
        @UniqueConstraint(
                name = "unique_dni", columnNames ="owner_dni"
        )
    }
)
public class Owner {

    private String firstName;
    private String lastName;
    private String dni;

}
