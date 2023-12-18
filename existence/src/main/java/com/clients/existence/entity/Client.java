package com.clients.existence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    private String cnp;
    private String firstname;
    private String lastname;
    private String documentid;
    private String documentexpdate;
}
