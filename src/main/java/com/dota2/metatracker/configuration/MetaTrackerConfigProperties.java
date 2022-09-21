package com.dota2.metatracker.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("metatracker")
public record MetaTrackerConfigProperties(String jwtToken) {
}
