package com.project.metasu.util.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
public class CommonCodeDetailId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "code", nullable=false)
    private CommonCodeMaster code;
    @Column(nullable=false)
    private String subCode;
}
