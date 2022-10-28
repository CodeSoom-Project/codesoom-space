package com.codesoom.myseat.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "send-grid")
public class SendGridProperties {

    private String apiKey;
    private String from;

    public SendGridProperties(final String apiKey, final String from) {
        this.apiKey = apiKey;
        this.from = from;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getFrom() {
        return from;
    }

}
