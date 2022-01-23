package com.dealwars.model;

import lombok.Data;

import java.util.List;

@Data
public class Offer {

    private String itemUrl;
    private String imageUrl;
    private String name;
    private String seller;
    private String price;
    private List<ExtraOffer> extraOfferList;

}
