package com.janbat.shopuz.captcha;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "google.recaptcha.key")
public class CaptchaSettings {
    private String site;
    private String secret;

    public void setSite(String site) {
        this.site = site;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getSite() {
        return site;
    }

    public String getSecret() {
        return secret;
    }
}

