package com.dealwars.constant;

import lombok.Data;

@Data
public final class ShopConst {

    private ShopConst() {
    }

    public static final String TRENDYOL = "trendyol.com";
    public static final String TRENDYOL_URL = "https://www.trendyol.com";
    public static final String TRENDYOL_SEARCH = "https://www.trendyol.com/sr?q=";

    public static final String NEWEGG = "newegg.com";
    public static final String NEWEGG_SEARCH = "https://www.newegg.com/p/pl?d=";

    public static final String TAPAZ = "tap.az";
    public static final String TAPAZ_URL = "https://www.tap.az";
    public static final String TAPAZ_SEARCH = "https://www.tap.az/elanlar?utf8=%E2%9C%93&q%5Bkeywords%5D=";

    public static final String SHOPAZ = "shop.az";
    public static final String SHOPAZ_URL = "https://www.shop.az";
    public static final String SHOPAZ_SEARCH = "https://shop.az/search/1?keyword=";

}
