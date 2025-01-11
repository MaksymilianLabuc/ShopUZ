/**
 * @file CaptchaSettings.java
 * @brief Klasa CaptchaSettings do przechowywania ustawień CAPTCHA.
 *
 * Ta klasa zawiera metody do ustawiania i pobierania kluczy CAPTCHA.
 */

package com.janbat.shopuz.captcha;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "google.recaptcha.key")
public class CaptchaSettings {
    private String site;
    private String secret;

    /**
     * @brief Ustawia klucz strony.
     *
     * @param site Klucz strony.
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * @brief Ustawia klucz tajny.
     *
     * @param secret Klucz tajny.
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * @brief Pobiera klucz strony.
     *
     * @return Klucz strony.
     */
    public String getSite() {
        return site;
    }

    /**
     * @brief Pobiera klucz tajny.
     *
     * @return Klucz tajny.
     */
    public String getSecret() {
        return secret;
    }
}
