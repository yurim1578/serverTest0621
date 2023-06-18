package com.project.metasu.item.dto;

import com.project.metasu.item.domain.entity.ItemMaster;
import com.project.metasu.member.domain.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDTO {
  private ItemMaster itemCode;
  private String itemColorCode;
  private Member memberId;
  private String reviewTitle;
  private String reviewContents;
  private int reviewScore;
  private String reviewMasterImg;
}
