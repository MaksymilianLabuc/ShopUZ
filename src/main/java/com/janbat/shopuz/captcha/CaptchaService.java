/**
 * @file CaptchaService.java
 * @brief Klasa CaptchaService do weryfikacji CAPTCHA.
 *
 * Ta klasa zawiera metody do weryfikacji CAPTCHA za pomocą Google reCAPTCHA.
 */

package com.janbat.shopuz.captcha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CaptchaService {
    private final CaptchaSettings captchaSettings;

    /**
     * @brief Konstruktor klasy CaptchaService.
     *
     * @param captchaSettings Ustawienia CAPTCHA.
     */
    @Autowired
    public CaptchaService(CaptchaSettings captchaSettings) {
        this.captchaSettings = captchaSettings;
    }

    /**
     * @brief Metoda do weryfikacji CAPTCHA.
     *
     * Ta metoda wysyła żądanie do Google reCAPTCHA API w celu weryfikacji odpowiedzi CAPTCHA.
     *
     * @param response Odpowiedź CAPTCHA od użytkownika.
     * @return Zwraca true, jeśli weryfikacja CAPTCHA zakończyła się sukcesem, w przeciwnym razie false.
     */
    public boolean verifyCaptcha(String response) {
        String url = "https://www.google.com/recaptcha/api/siteverify";
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("secret", captchaSettings.getSecret());
        params.add("response", response);

        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(url, params, Map.class);
        Map<String, Object> body = responseEntity.getBody();
        return (Boolean) body.get("success");
    }
}
