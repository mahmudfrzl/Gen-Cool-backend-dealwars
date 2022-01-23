package com.dealwars.controller;

import com.dealwars.model.Offer;
import com.dealwars.entity.ProductDetail;
import com.dealwars.model.ProductRequest;
import com.dealwars.service.ProductDetailService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DealController {

    private ProductDetailService productDetailService;

    public DealController(ProductDetailService productDetailService) {
        this.productDetailService = productDetailService;
    }

    @GetMapping("/getProduct")
    List<ProductDetail> getProductList(@RequestParam(name = "userInput") String userInput) {
        return productDetailService.getProduct(userInput);
    }

    @PostMapping("/searchProduct")
    public void searchProduct(@Valid @RequestBody ProductRequest productRequest) {
        productDetailService.searchProduct(productRequest);
    }

    @GetMapping("/getOffer")
    Optional<Offer> getOffer(@RequestParam(name = "userInput") String userInput) {
        return productDetailService.getOffer(userInput);
    }

}
