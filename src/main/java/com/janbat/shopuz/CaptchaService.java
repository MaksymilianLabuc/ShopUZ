package com.janbat.shopuz;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.logging.Logger;

@Service
public class CaptchaService {
    private final CaptchaSettings captchaSettings;
    
    @Autowired
    public CaptchaService(CaptchaSettings captchaSettings) {
        this.captchaSettings = captchaSettings;
    }

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

