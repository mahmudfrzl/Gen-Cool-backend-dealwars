package com.dealwars.util;

import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
public final class Util {

    private Util() {
    }

    public static String identifyUrl(String url) {
        log.info("URL identifying: " + url);
        String domain = "";

        try {
            domain = new URI(url).getHost();
        } catch (URISyntaxException e) {
            log.error(e.getMessage());
        }
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

}