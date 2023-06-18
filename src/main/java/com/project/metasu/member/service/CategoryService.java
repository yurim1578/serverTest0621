package com.project.metasu.member.service;

import com.project.metasu.item.domain.entity.ItemImg;
import com.project.metasu.item.domain.entity.ItemMaster;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {

public Page<ItemMaster> getList(int page,int pageSize,String capacity ,String type,String from,String method);

public List<ItemImg> getImg();




}
