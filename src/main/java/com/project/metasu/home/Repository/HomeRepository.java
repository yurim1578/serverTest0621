package com.project.metasu.home.Repository;

import com.project.metasu.home.Dto.HomeItemDto;
import com.project.metasu.item.domain.entity.Cart;
import com.project.metasu.item.domain.entity.ItemMaster;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class HomeRepository {

    private final EntityManager em;
    public void save(ItemMaster itemMaster) { em.persist((itemMaster));}
    public ItemMaster findByItemCode(String itemCode) {
        return em.find(ItemMaster.class, itemCode);
    }
    public List<ItemMaster> findByItemName(String itemName) {

        return em.createQuery("select im from ItemMaster im where im.itemName=:itemName", ItemMaster.class)
            .setParameter("im.itemName", itemName)
            .getResultList();
    }
    public List<HomeItemDto> findAll() {
        return em.createQuery("select new com.project.metasu.home.HomeItemDto(im.itemCode, im.itemName, im.itemPrice, im.itemMasterImg) from ItemMaster im", HomeItemDto.class)
            .getResultList();

    }
    @Transactional
    public void insertCart(Cart cart) {
        em.persist(cart);
    }
}
