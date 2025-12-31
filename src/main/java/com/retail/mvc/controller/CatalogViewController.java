package com.retail.mvc.controller;

import com.retail.mvc.client.CatalogClient;
import com.retail.mvc.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class CatalogViewController {

    private final CatalogClient client;

    public CatalogViewController(CatalogClient client) {
        this.client = client;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", client.getAll());
        return "products";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Product product) {
        if (product.getProductId() == null) {
            client.add(product);
        } else {
            client.update(product);
        }
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int productId, Model model) {
        model.addAttribute("product", client.getById(productId));
        return "product-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int productId) {
        client.delete(productId);
        return "redirect:/products";
    }
}