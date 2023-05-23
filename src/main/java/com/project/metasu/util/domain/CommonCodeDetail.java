package com.project.metasu.util.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class CommonCodeDetail {
   @EmbeddedId
   private CommonCodeDetailId commonCodeDetailId;
   @Column(nullable=false)
   private String subCodeName;
}
