package com.retail.mvc.controller;

import com.retail.mvc.client.CatalogClient;
import com.retail.mvc.model.Category;
import com.retail.mvc.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class CatalogViewController {

    private final CatalogClient client;

    public CatalogViewController(CatalogClient client) {
        this.client = client;
    }

    @GetMapping
    public String list(@RequestParam(required = false) Integer categoryId, Model model) {
        List<Category> categories = client.getAllCategories();
        List<Product> products = (categoryId != null)
                ? client.getProductsByCategory(categoryId)
                : client.getAll();
        Map<Integer, String> categoryMap = categories.stream()
                .collect(Collectors.toMap(
                        Category::getCategoryId,  // assuming getCategoryId() exists
                        Category::getName        // assuming getName() exists
                ));

        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategoryId", categoryId);
        model.addAttribute("categoryMap", categoryMap);
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

    @GetMapping("/edit/{productId}")
    public String edit(@PathVariable int productId, Model model) {
        model.addAttribute("product", client.getById(productId));
        return "product-form";
    }

    @GetMapping("/delete/{productId}")
    public String delete(@PathVariable int productId) {
        client.delete(productId);
        return "redirect:/products";
    }
}