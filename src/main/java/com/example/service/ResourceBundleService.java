package com.example.service;

import com.example.enums.AppLanguage;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ResourceBundleService {
    private final ResourceBundleMessageSource resourceBundleMessageSource;

    public ResourceBundleService(ResourceBundleMessageSource resourceBundleMessageSource) {
        this.resourceBundleMessageSource = resourceBundleMessageSource;
    }

    public String getMessage(String code, AppLanguage appLanguage) {
        return resourceBundleMessageSource.getMessage(code, null, new Locale(appLanguage.name()));
    }

}

