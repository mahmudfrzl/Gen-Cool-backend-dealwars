package com.dealwars.service;

import com.dealwars.constant.ShopConst;
import com.dealwars.model.ExtraOffer;
import com.dealwars.model.Offer;
import com.dealwars.entity.ProductDetail;
import com.dealwars.model.ProductRequest;
import com.dealwars.repository.ProductDetailsRepository;
import com.dealwars.util.ProductUtil;
import com.dealwars.util.SearchUtil;
import com.dealwars.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class ProductDetailService {

    ProductDetailsRepository productDetailsRepository;

    public ProductDetailService(ProductDetailsRepository productDetailsRepository) {
        this.productDetailsRepository = productDetailsRepository;
    }

    public void searchProduct(ProductRequest productRequest) {

        var productList = SearchUtil.searchStore(productRequest.getProductName());

        log.info("Product Searching: " + productRequest.getProductName());

        for (String s : productList) {
            var productOrigin = Util.identifyUrl(s);
            if (productOrigin.equals(ShopConst.TRENDYOL)) {
                var productDetail = ProductUtil.parseTrendyol(s);
                productDetail.setUserInput(productRequest.getProductName());
                productDetailsRepository.save(productDetail);
            }
            if (productOrigin.equals(ShopConst.NEWEGG)) {
                var productDetail = ProductUtil.parseNewEgg(s);
                productDetail.setUserInput(productRequest.getProductName());
                productDetailsRepository.save(productDetail);
            }
            if (productOrigin.equals(ShopConst.TAPAZ)) {
                var productDetail = ProductUtil.parseTapaz(s);
                productDetail.setUserInput(productRequest.getProductName());
                productDetailsRepository.save(productDetail);
            }
            if (productOrigin.equals(ShopConst.SHOPAZ)) {
                var productDetail = ProductUtil.parseShopAz(s);
                productDetail.setUserInput(productRequest.getProductName());
                productDetailsRepository.save(productDetail);
            }
        }

    }

    public List<ProductDetail> getProduct(String input) {
        return productDetailsRepository.findAllByUserInput(input);
    }

    public Optional<Offer> getOffer(String input) {

        log.info("Getting Offer!!");

        var offerList = productDetailsRepository.findTop4ByUserInputOrderByPrice(input);
        Offer offer = new Offer();
        List<ExtraOffer> extraOfferList = new ArrayList<>();

        if (!offerList.isEmpty()) {

            var itemUrl = offerList.get(0).getProductUrl();
            var imageUrl = offerList.get(0).getImageUrl();
            var name = offerList.get(0).getName();
            var seller = offerList.get(0).getSource();
            var price = offerList.get(0).getPrice();

            for (int i = 1; i < offerList.size(); i++) {
                ExtraOffer extraOffer = new ExtraOffer();
                var extraOfferSource = offerList.get(i).getSource();
                var extraOfferPrice = offerList.get(i).getPrice();
                var extraOfferProductUrl = offerList.get(i).getProductUrl();

                extraOffer.setItemUrl(extraOfferProductUrl);
                extraOffer.setPrice(extraOfferPrice);
                extraOffer.setSeller(extraOfferSource);

                if (!extraOfferList.contains(extraOffer)) {
                    extraOfferList.add(extraOffer);
                    extraOfferList.sort(Comparator.comparing(ExtraOffer::getPrice));
                }
            }

            offer.setItemUrl(itemUrl);
            offer.setImageUrl(imageUrl);
            offer.setName(name);
            offer.setSeller(seller);
            offer.setPrice(price);
            offer.setExtraOfferList(extraOfferList);

            return Optional.of(offer);
        }
        return Optional.empty();
    }

}
