package com.mferpena.infrastructure.secondary.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@SuppressWarnings("unused")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    @SequenceGenerator(name = "client_seq", sequenceName = "client_seq", initialValue = 10000, allocationSize = 1)
    private Long id;
    private String name;
    private String paternalLastName;
    private String maternalLastName;
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    private Boolean status;

    @PrePersist
    protected void onCreate() {
        dateCreate = new Date();
    }
}
