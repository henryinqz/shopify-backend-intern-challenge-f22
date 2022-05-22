package com.henryinqz.shopifybackendinternchallengef22.controller;

import com.henryinqz.shopifybackendinternchallengef22.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @Autowired
    InventoryService inventoryService;

    @GetMapping
    public String listInventory(Model model) {
        model.addAttribute("inventory", inventoryService.list().getBody());
        return "list-inventory";
    }
}
