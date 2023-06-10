package com.project.metasu.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//  http://localhost:8090/test/main
@Controller
@RequiredArgsConstructor
@RequestMapping("/test")
public class ItemController {

    @GetMapping("/main")
    public String view(Model model) {
        return "/item/itemDetail";
    }

}
