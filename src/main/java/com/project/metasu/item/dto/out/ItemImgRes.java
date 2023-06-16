package com.project.metasu.item.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
public class ItemImgRes {

    private String itemImg1;
    private String itemImg2;
    private String itemImg3;
    @Builder
    public ItemImgRes(String itemImg1, String itemImg2, String itemImg3) {
        this.itemImg1 = itemImg1;
        this.itemImg2 = itemImg2;
        this.itemImg3 = itemImg3;
    }
}
