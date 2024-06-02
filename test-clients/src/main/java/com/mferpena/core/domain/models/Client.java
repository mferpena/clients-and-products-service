package com.mferpena.core.domain.models;

import lombok.Data;

import java.util.Date;

@Data
public class Client {
    private Long id;
    private String name;
    private String paternalLastName;
    private String maternalLastName;
    private Date dateCreate;
    private Boolean status;
}
