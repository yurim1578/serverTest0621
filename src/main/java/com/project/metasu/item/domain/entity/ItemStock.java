package com.project.metasu.item.domain.entity;

import com.project.metasu.util.converter.BooleanToYnConverter;
import com.project.metasu.util.domain.EssentialDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemStock extends EssentialDate {
    @Id
    private String itemBarcode;                                 // 상품 바코드(일련번호)

    private String itemCode;
    @MapsId("itemCode")
    @ManyToOne
    @JoinColumn(name = "itemCode", nullable=false)
    private ItemMaster itemMaster;                               // 상품 코드

    @Column(nullable=false)
    private String itemColorCode;                                // 상품 색상 코드
    @Convert(converter = BooleanToYnConverter.class)
    @Column(nullable=false)
    private Boolean salesYn;                                     // 상품 판매여부
    private LocalDateTime salesDate;                             // 상품 판매일

    @Builder
    public ItemStock(String itemBarcode, ItemMaster itemMaster, String itemColorCode, Boolean salesYn, LocalDateTime salesDate) {
        this.itemBarcode = itemBarcode;
        this.itemCode = itemMaster.getItemCode();
        this.itemColorCode = itemColorCode;
        this.salesYn = salesYn;
        this.salesDate = salesDate;
    }

    // 재고 판매여부 Y로 변경
    public void setSalesYn(Boolean salesYn) {
        this.salesYn = salesYn;
        this.salesDate = LocalDateTime.now();
    }
}
