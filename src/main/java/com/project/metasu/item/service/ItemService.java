package com.project.metasu.item.service;

import com.project.metasu.item.dto.in.CartReq;
import com.project.metasu.item.dto.in.PaymentReq;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Map;

public interface ItemService {
    ResponseEntity findItem(String itemCode);
    ResponseEntity findItemColorCode(String itemCode);
    ResponseEntity findItemImg(String itemCode);
    ResponseEntity findItemImgOfColor(String itemCode, String itemColorCode);
    Boolean chkCart(CartReq req);
    ResponseEntity addCart(CartReq req);
    ResponseEntity findCart(String memberId);
    ResponseEntity deleteCart(String itemCode, String itemColorCode, String memberId);
    ResponseEntity findByMember(String memberId);
    ResponseEntity findAllByMemberId(String memberId);
    Map<String,Object> findByRentalOrderInfo(String itemCode, String itemColorCode);
    // ResponseEntity findReviewByItemCode(String itemCode, String sortValue);
    ResponseEntity findByItemCode(String itemCode, String sortValue, Pageable pageable);
    Boolean chkInstallDate(LocalDate installDate, String installTimeCode);
    // ResponseEntity addOrder(OrderReq req);
    ResponseEntity addPayment(PaymentReq req);
}
