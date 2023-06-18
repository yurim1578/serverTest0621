package com.project.metasu.util.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@IdClass(CommonCodeDetailId.class)
public class CommonCodeDetail {
   @Id
   private String code;

   @Id
   private String subCode;

   @MapsId("code")
   @ManyToOne
   @JoinColumn(name = "code", nullable=false, insertable = false, updatable = false)
   private CommonCodeMaster commonCodeMaster;

   @Column(nullable=false)
   private String subCodeName;
}
