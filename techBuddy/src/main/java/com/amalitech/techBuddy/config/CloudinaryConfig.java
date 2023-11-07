package com.amalitech.techBuddy.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    private final String CLOUD_NAME = "tech-buddy";
    private final String API_KEY = "349132473821485";
    private final String API_SECRET = "Z-W-O5qkjn1gGeli3zDfil1pF14";

    @Bean
    public Cloudinary cloudinary(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name",CLOUD_NAME);
        config.put("api_key", API_KEY);
        config.put("api_secret", API_SECRET);

        return new Cloudinary(config);
    }

}
