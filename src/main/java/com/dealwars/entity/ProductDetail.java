package com.dealwars.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_detail_id_seq")
    @SequenceGenerator(name = "product_detail_id_seq", sequenceName = "product_detail_id_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String price;
    private String source;
    private String imageUrl;
    private String productUrl;
    private String userInput;

}
