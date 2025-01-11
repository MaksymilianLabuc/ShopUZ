/**
 * @file LocaleConfig.java
 * @brief Klasa LocaleConfig konfigurująca lokalizację aplikacji.
 *
 * Ta klasa zawiera konfigurację lokalizacji aplikacji, w tym zarządzanie rozpoznawaniem lokalizacji, zmianą lokalizacji oraz źródłem komunikatów.
 */

package com.janbat.shopuz;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

/**
 * @brief Klasa LocaleConfig konfigurująca lokalizację aplikacji.
 */
@Configuration
public class LocaleConfig implements WebMvcConfigurer {

    /**
     * @brief Konfiguruje rozpoznawanie lokalizacji.
     * @return Obiekt LocaleResolver do rozpoznawania lokalizacji.
     */
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        return localeResolver;
    }

    /**
     * @brief Konfiguruje interceptor zmiany lokalizacji.
     * @return Obiekt LocaleChangeInterceptor do zmiany lokalizacji.
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    /**
     * @brief Dodaje interceptory do rejestru.
     * @param registry Rejestr interceptorów.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    /**
     * @brief Konfiguruje źródło komunikatów.
     * @return Obiekt MessageSource do zarządzania komunikatami.
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
