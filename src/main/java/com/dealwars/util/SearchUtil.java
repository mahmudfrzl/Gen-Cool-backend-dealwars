package com.dealwars.util;

import com.dealwars.constant.ShopConst;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SearchUtil {

    private SearchUtil() {
    }

    public static List<String> searchStore(String product) {

        List<String> productList = new ArrayList<>();
        Document trendyolProductUrl = null;
        Document neweggProductUrl = null;
        Document tapazProductUrl = null;
        Document shopazProductUrl = null;

        try {

            trendyolProductUrl = Jsoup.connect(ShopConst.TRENDYOL_SEARCH + product).get();
            neweggProductUrl = Jsoup.connect(ShopConst.NEWEGG_SEARCH + product.replace("?", "-").replace(" ", "-")).get();
            tapazProductUrl = Jsoup.connect(ShopConst.TAPAZ_SEARCH + product).get();
            shopazProductUrl = Jsoup.connect(ShopConst.SHOPAZ_SEARCH + product).get();

        } catch (IOException e) {
            e.printStackTrace();


        }
        Element trendyolProducts = null;
        Elements neweggProducts = null;
        Elements tapazProducts = null;
        Elements shopazProducts = null;

        try {
            trendyolProducts = trendyolProductUrl.getElementsByClass("prdct-cntnr-wrppr").get(0);
            neweggProducts = neweggProductUrl.getElementsByClass("item-img");
            tapazProducts = tapazProductUrl.getElementsByClass("products-link");
            shopazProducts = shopazProductUrl.getElementsByClass("carousel-cell text-decoration-none");
        } catch (NullPointerException ex) {
            log.error(ex.getMessage());
        }


        var trendyolProduct = trendyolProducts.getElementsByTag("a");

        try {

            productList.add(ShopConst.TAPAZ_URL + tapazProducts.get(8).attr("href"));
            productList.add(ShopConst.TAPAZ_URL + tapazProducts.get(9).attr("href"));
            productList.add(ShopConst.TAPAZ_URL + tapazProducts.get(10).attr("href"));

            productList.add(neweggProducts.get(4).attr("href"));
            productList.add(neweggProducts.get(5).attr("href"));
            productList.add(neweggProducts.get(6).attr("href"));

            productList.add(ShopConst.SHOPAZ_URL + shopazProducts.get(0).attr("href"));
            productList.add(ShopConst.SHOPAZ_URL + shopazProducts.get(1).attr("href"));
            productList.add(ShopConst.SHOPAZ_URL + shopazProducts.get(2).attr("href"));

        } catch (IndexOutOfBoundsException ex) {
            log.error(ex.getMessage());
        }

        for (int i = 0; i < 3; i++) {
            try {
                productList.add(ShopConst.TRENDYOL_URL + trendyolProduct.get(i).attr("href"));
            } catch (IndexOutOfBoundsException ex) {
                log.error(ex.getMessage());
                break;
            }
        }

        return productList;
    }

}
