package com.project.metasu.member.service.impl;

import com.project.metasu.item.domain.entity.ItemImg;
import com.project.metasu.item.domain.entity.ItemMaster;
import com.project.metasu.item.repository.ItemMasterRepository;
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

    private final ItemMasterRepository itemMasterRepository;
    //private final ImgRepository imgRepository;

    @Override
    public Page<ItemMaster> getList(int page, int pagesize, String capacity, String type, String from, String method, String sort) {
        List<Sort.Order> sorts = new ArrayList<>();

        if(sort==null){
            sorts.add(Sort.Order.desc("itemMakeDate"));
        }else if(sort.equals("최신순")) {
            sorts.add(Sort.Order.desc("itemMakeDate")); // 제조일자 내림차순 정렬
        } else if(sort.equals("과거순")){
            sorts.add(Sort.Order.asc("itemMakeDate")); // 제조일자 내림차순 정렬
        } else if(sort.equals("낮은 가격순")){
            sorts.add(Sort.Order.asc("itemPrice"));
        } else if(sort.equals("높은 가격순")){
            sorts.add(Sort.Order.desc("itemPrice"));
        }else{
            sorts.add(Sort.Order.desc("itemMakeDate"));
        }

        Pageable pageable = PageRequest.of(page - 1, pagesize, Sort.by(sorts));
        if (capacity != null && !capacity.isEmpty()) {
            return this.itemMasterRepository.findByItemTankCapacityContainingIgnoreCase(pageable, capacity);
        } else if (type != null && !type.isEmpty()) {
            return this.itemMasterRepository.findByItemIntalTypeContainingIgnoreCase(pageable, type);
        } else if (from != null && !from.isEmpty()) {
            return this.itemMasterRepository.findByItemFromContainingIgnoreCase(pageable, from);
        } else if(method!=null&&!method.isEmpty()) {
            return this.itemMasterRepository.findByItemWaterMethodContainingIgnoreCase(pageable,method);
        }else{
            return this.itemMasterRepository.findAll(pageable);
        }
    }


    @Override
    public List<ItemImg> getImg() {
        return null;
    }

}
