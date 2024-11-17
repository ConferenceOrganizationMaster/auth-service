package com.bulatmain.authorization.config;

import com.osohq.oso_cloud.Oso;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OsoConfig {
    private Environment environment;

    @Bean
    Oso oso() {
        return new Oso(Objects.requireNonNull(environment.getProperty("OSO_API_KEY")));
    }
}
