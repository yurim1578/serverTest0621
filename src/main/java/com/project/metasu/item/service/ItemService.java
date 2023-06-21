package com.project.metasu.item.service;

import com.project.metasu.item.dto.in.AutoPaymentReq;
import com.project.metasu.item.dto.in.CartReq;
import com.project.metasu.item.dto.in.PaymentReq;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

public interface ItemService {
    ResponseEntity findItem(String itemCode); // 특정 상품 주요 정보(item_master)
    ResponseEntity findItemColorCode(String itemCode);  // 특정 상품에 대한 전체 색상 정보
    ResponseEntity findItemImg(String itemCode);    // 한 상품 이미지
    ResponseEntity findItemImgOfColor(String itemCode, String itemColorCode);  // 한 상품에 대한 컬러코드별 이미지
    Boolean chkCart(CartReq req); // 장바구니 데이터가 있는지 확인
    ResponseEntity addCart(CartReq req);  // 장바구니 insert/update
    ResponseEntity findCart(String memberId);  // user 장바구니 데이터
    ResponseEntity deleteCart(String itemCode, String itemColorCode, String memberId);  // 장바구니 삭제
    ResponseEntity findByMember(String memberId);   // user 정보
    ResponseEntity findAllByMemberId(String memberId); // user에 대한 장바구니 모든 데이터
    Map<String,Object> findByRentalOrderInfo(String itemCode, String itemColorCode); // 렌탈시 주문목록에서 보여줘야할 추가 정보들
    ResponseEntity findByItemCode(String itemCode, String sortValue, Pageable pageable); // 선택한 상품에 대한 모든 리뷰
    Boolean chkInstallDate(LocalDate installDate, String installTimeCode);  // 설치 날짜 마감되었는지 체크
    ResponseEntity addPayment(PaymentReq req);  // 구매로 주문 (직접 결제)

    ResponseEntity autoPayment(String authKey,String customerKey, String str) throws IOException, InterruptedException; // 렌탈 정기결제 (빌링키 생성 + 주문 save)
    ResponseEntity acceptPayment(String rentalNo) throws IOException, InterruptedException; // 배치 돌아갈때 결제 승인 + 결제 save
    ResponseEntity notAcceptPayment(String rentalNo) throws IOException, InterruptedException;  // 결제실패 or 취소시
}
