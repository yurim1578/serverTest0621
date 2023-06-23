package com.project.metasu.admin.repository;

import com.project.metasu.item.domain.entity.OrderMaster;
import com.project.metasu.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface AdminOrderRepository extends JpaRepository<OrderMaster, String> {
  int countByMemberId(String memberId);

  @Query(value="select o.order_no, c.contract_status, d.sub_code_name, o.member_id, r.rental_pay_auto_date, r.rental_no "+
      "from order_master o join contract c on o.contract_no=c.contract_no "+
      "join common_code_detail d on c.contract_status=d.sub_code "+
      "left outer join rental r on o.rental_no=r.rental_no "+
      "where d.code='CS'",nativeQuery = true)
  List<Map<String,Object>> getOderList();

  @Query(value = "select c.contract_name, c.contract_no, c.contract_status, o.order_no, m.member_id, m.order_status, i.item_code, i.item_name, i.item_price, d.delivery_status, m.created_date " +
      ",r.rental_status, r.rental_start_date, r.rental_end_date, r.rental_pay_auto_date, r.rental_pay_auto_yn, r.rental_no " +
      ", p.payment_status, p.payment_amount, p.payment_type, s.item_barcode " +
      "from order_detail o join order_master m on o.order_no=m.order_no join item_stock s on o.item_barcode=s.item_barcode " +
      "join payment p on p.payment_no=m.payment_no left outer join rental r on r.rental_no=m.rental_no join delivery d on d.delivery_no=m.delivery_no " +
      "join item_master i on i.item_code=s.item_code join contract c on c.contract_no=m.contract_no " +
      "where m.order_no=:orderNo",nativeQuery = true)
  List<Map<String,Object>> getOrderDetail(@Param("orderNo")String orderNo);

  @Query(value="select c.*, o.order_no from order_master o join contract c on c.contract_no=o.contract_no where c.contract_no=:contractNo",nativeQuery = true)
  Map<String,Object> findContract(@Param("contractNo") String contractNo);

  @Query(value="select distinct e.* from common_code_detail e, order_master o " +
      "join payment p on o.payment_no=p.payment_no " +
      "join contract c on o.contract_no=c.contract_no " +
      "join delivery d on o.delivery_no=d.delivery_no " +
      "left outer join rental r on o.rental_no=r.rental_no " +
      "where (e.sub_code=o.order_status and e.code='OD') or (e.sub_code=p.payment_status and e.code='PS') " +
      "or (e.sub_code=c.contract_status and e.code='CS') or (e.sub_code=d.delivery_status and e.code='DS') " +
      "or (e.sub_code=r.rental_status and e.code='RTS') and o.order_no=:orderNo order by e.code",nativeQuery = true)
  List<Map<String,Object>> findSubCodeName(@Param("orderNo")String orderNo);


}
