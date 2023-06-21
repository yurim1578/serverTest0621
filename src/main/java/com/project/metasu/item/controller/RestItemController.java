package com.project.metasu.item.controller;

import com.project.metasu.item.dto.in.CartReq;
import com.project.metasu.item.dto.in.PaymentReq;
import com.project.metasu.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class RestItemController {

    private final ItemService itemService;

    // 컬러코드별 이미지
    @PostMapping("/findItemColorCodeImg")
    public ResponseEntity findItemColorCodeImg(String itemCode, String itemColorCode) {
        ResponseEntity result = itemService.findItemImgOfColor(itemCode, itemColorCode);
        return result;
    }

    // 장바구니에 데이터가 있는지 체크
    @PostMapping("/chkCart")
    public Boolean chkCart(CartReq req){
        Boolean result = itemService.chkCart(req);
        return result;
    }

    // 장바구니 데이터 insert/update
    @PostMapping("/addCart")
    public ResponseEntity addCart(CartReq req) {
        ResponseEntity status = itemService.addCart(req);
        return status;
    }

    // 장바구니 삭제
    @PostMapping("/deleteCart")
    public ResponseEntity deleteCart(String itemCode, String itemColorCode, String memberId) {
        ResponseEntity status = itemService.deleteCart(itemCode, itemColorCode, memberId);
        return status;
    }

    // 설치 날짜와 시간이 마감되었는지 체크
    @PostMapping("/chkInstallDate")
    public Boolean chkInstallDate(@RequestParam("installDate") @DateTimeFormat(pattern = "yyyyMMdd") LocalDate installDate, String installTimeCode) {
        Boolean result = itemService.chkInstallDate(installDate,installTimeCode);
        return result;
    }


}
