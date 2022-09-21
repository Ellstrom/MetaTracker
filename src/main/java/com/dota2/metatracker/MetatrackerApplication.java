package com.dota2.metatracker;

import com.dota2.metatracker.configuration.MetaTrackerConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(MetaTrackerConfigProperties.class)
public class MetatrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetatrackerApplication.class, args);
    }

}
