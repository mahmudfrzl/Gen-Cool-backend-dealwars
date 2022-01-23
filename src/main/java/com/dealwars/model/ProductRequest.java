package com.dealwars.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductRequest {
    @NotBlank(message = "Məhsul adı boş ola bilməz")
    private String productName;
}
