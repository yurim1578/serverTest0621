package com.project.metasu.item.dto.out;

import com.project.metasu.item.domain.entity.ItemMaster;
import com.project.metasu.item.domain.entity.Review;
import com.project.metasu.member.domain.entity.Member;
import com.project.metasu.util.domain.CommonCodeDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class ReviewRes {

}
