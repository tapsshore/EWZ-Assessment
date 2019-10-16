package com.assessment.test.gtassessment.utils.i18.impl;

import com.assessment.test.gtassessment.utils.i18.api.MessageService;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class MessageServiceImpl implements MessageService {
    private MessageSource messageSource;

    public MessageServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @Override
    public String getMessage(String propertyName, String[] placeholders, Locale locale) {
        return messageSource.getMessage(propertyName, placeholders, locale);
    }

}
