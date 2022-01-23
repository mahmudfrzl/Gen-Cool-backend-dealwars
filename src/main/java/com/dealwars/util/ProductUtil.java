package com.dealwars.util;

import com.dealwars.entity.ProductDetail;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Slf4j
public final class ProductUtil {

    static Document productUrl;

    private ProductUtil() {
    }

    public static ProductDetail parseTrendyol(String url) {

        log.info("Trendyol URL Parsing: " + url);

        ProductDetail productDetail = new ProductDetail();

        try {
            productUrl = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Elements productUrlElementsByClass = productUrl.getElementsByClass("product-container");

        for (Element element : productUrlElementsByClass) {
            productDetail.setSource("trendyol");
            productDetail.setProductUrl(url);
            productDetail.setPrice(element.
                    getElementsByClass("prc-slg").text());
            productDetail.setName(element.
                    getElementsByClass("pr-new-br").text());
            productDetail.setImageUrl(element.
                    getElementsByTag("img").attr("src"));
        }
        var aa = productDetail.getPrice().split(" ");
        var item = aa[0].replace(".", "").replace(",", ".");
        DecimalFormat df = new DecimalFormat("###.##");
        var newPrice = df.format(Double.parseDouble(item) * 0.13d) + " AZN";

        productDetail.setPrice(newPrice);

        log.info("Trendyol URL Parsing Finished: " + url);

        return productDetail;
    }

    public static ProductDetail parseTapaz(String url) {

        log.info("Tap.az URL Parsing: " + url);

        ProductDetail productDetail = new ProductDetail();

        try {
            productUrl = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        productDetail.setSource("tap.az");
        productDetail.setProductUrl(url);
        productDetail.setPrice(productUrl.
                getElementsByClass("middle").text().replace(" ", "").replace("AZN", " AZN"));
        productDetail.setName(productUrl.
                getElementsByClass("title-container").first().getElementsByTag("h1").text());
        productDetail.setImageUrl(productUrl.
                getElementsByClass("large-photo").first().getElementsByTag("a").attr("href"));


        log.info("Tap.az URL Parsing Finished: " + url);

        return productDetail;
    }

    public static ProductDetail parseNewEgg(String url) {

        log.info("Newegg.com URL Parsing: " + url);

        ProductDetail productDetail = new ProductDetail();

        try {
            productUrl = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }


        var price = productUrl.getElementsByClass("price-current").get(0).text();
        var newPrice = "";

        var format = NumberFormat.getCurrencyInstance(Locale.US);
        DecimalFormat df = new DecimalFormat("###.##");
        try {
            Number tempPrice = format.parse(price);
            newPrice = df.format(tempPrice.doubleValue() * 1.7d) + " AZN";
        } catch (ParseException e) {
            log.error(e.getMessage());
        }


        productDetail.setSource("newegg.com");
        productDetail.setProductUrl(url);
        productDetail.setPrice(newPrice);
        productDetail.setName(productUrl.
                getElementsByClass("product-title").text());
        productDetail.setImageUrl(productUrl.
                getElementsByClass("product-view-img-original").attr("src"));

        log.info("Newegg.com URL Parsing Finished: " + url);

        return productDetail;
    }

    public static ProductDetail parseShopAz(String url) {

        log.info("Shop.az URL Parsing: " + url);

        ProductDetail productDetail = new ProductDetail();

        try {
            productUrl = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        var name = productUrl.
                getElementsByClass("product-name display-middle font-demi font-weight-normal fs-28").text();
        var price = productUrl.
                getElementsByClass("product-information-price-actual font-demi color-purple fs-24 m-r-8 ng-star-inserted").text();
        var image_url = productUrl.
                getElementsByClass("product-img-holder").get(0).getElementsByTag("img").attr("src");

        productDetail.setSource("shop.az");
        productDetail.setProductUrl(url);
        productDetail.setPrice(price);
        productDetail.setName(name);
        productDetail.setImageUrl(image_url);

        log.info("Shop.az URL Parsing Finished: " + url);

        return productDetail;
    }

}
