package com.project.metasu.item.domain.entity;

import com.project.metasu.util.domain.EssentialDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class ReviewImg extends EssentialDate implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "reviewNo")
    private Review reviewNo;           // 리뷰 번호
    @Column(nullable=false)
    private String reviewMasterImg;    // 리뷰 대표 이미지
    private String reviewImg1;         // 리뷰 이미지1
    private String reviewImg2;         // 리뷰 이미지2
    private String reviewImg3;         // 리뷰 이미지3
}
