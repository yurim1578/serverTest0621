package com.project.metasu.member.service.impl;

import com.project.metasu.item.domain.entity.ItemImg;
import com.project.metasu.item.domain.entity.ItemMaster;
import com.project.metasu.item.repository.ItemRepository;
import com.project.metasu.member.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final ItemRepository itemRepository;
    //private final ImgRepository imgRepository;

    @Override
    public Page<ItemMaster> getList(int page, int pagesize, String capacity, String type, String from, String method) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("itemMakeDate")); // 제조일자 내림차순 정렬


        Pageable pageable = PageRequest.of(page - 1, pagesize, Sort.by(sorts));
        if (capacity != null && !capacity.isEmpty()) {
            return this.itemRepository.findByItemTankCapacityContainingIgnoreCase(pageable, capacity);
        } else if (type != null && !type.isEmpty()) {
            return this.itemRepository.findByItemIntalTypeContainingIgnoreCase(pageable, type);
        } else if (from != null && !from.isEmpty()) {
            return this.itemRepository.findByItemFromContainingIgnoreCase(pageable, from);
        } else if(method!=null&&!method.isEmpty()) {
            return this.itemRepository.findByItemWaterMethodContainingIgnoreCase(pageable,method);
        }else{
            return this.itemRepository.findAll(pageable);
        }
    }


    @Override
    public List<ItemImg> getImg() {
        return null;
    }

}
