package org.example.anikudasaikodik.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// KodikProperties.java
@Component
@ConfigurationProperties(prefix = "kodik")
@Getter
@Setter
public class KodikProperties {
    private String url;
    private String token;
}
