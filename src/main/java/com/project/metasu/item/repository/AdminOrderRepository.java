package com.project.metasu.item.repository;

import com.project.metasu.item.domain.entity.OrderMaster;
import com.project.metasu.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface AdminOrderRepository extends JpaRepository<OrderMaster, String> {
  int countByMemberId(Member memberId);

  @Query(value="select o.order_no, m.member_id, m.order_status,c.sub_code_name, n.item_code, n.item_name " +
      "from order_detail o join order_master m on o.order_no=m.order_no " +
      "join item_stock i on o.item_barcode=i.item_barcode " +
      "join item_master n on i.item_code=n.item_code join common_code_detail c on m.order_status=c.sub_code where c.code='OD'",nativeQuery = true)
  List<Map<String,Object>> getOderList();

  @Query(value = "select c.contract_no, o.order_no, m.member_id, m.order_status, i.item_code, i.item_name, i.item_price, d.delivery_status, m.created_date " +
      ",r.rental_status, r.rental_start_date, r.rental_end_date, r.rental_pay_auto_date, r.rental_rental_pay_auto_yn, r.rental_no " +
      ", p.payment_status, p.payment_amount, p.payment_type " +
      "from order_detail o join order_master m on o.order_no=m.order_no join item_stock s on o.item_barcode=s.item_barcode " +
      "join payment p on p.payment_no=m.payment_no left outer join rental r on r.rental_no=m.rental_no join delivery d on d.delivery_no=m.delivery_no " +
      "join item_master i on i.item_code=s.item_code join contract c on c.contract_no=m.contract_no " +
      "where m.order_no=:orderNo",nativeQuery = true)
  Map<String,Object> getOrderDetail(@Param("orderNo")String orderNo);

  @Query(value="select c.*, o.order_no from order_master o join contract c on c.contract_no=o.contract_no where c.contract_no=:contractNo",nativeQuery = true)
  Map<String,Object> findContract(@Param("contractNo") String contractNo);
}
