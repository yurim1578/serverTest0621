package com.project.metasu.admin.controller;

import com.project.metasu.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class RestAdminController {

  private final ItemService item_itemService;
  @PostMapping("/findItemColorCodeImg")
  public ResponseEntity findItemColorCodeImg(String itemCode, String itemColorCode) {
    ResponseEntity result = item_itemService.findItemImgOfColor(itemCode, itemColorCode);
    return result;
  }

}
