package com.project.metasu.util.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Entity
public class CommonCodeMaster {
    @Id
    private String code;
    @Column(nullable=false)
    private String codeName;
}
