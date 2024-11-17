package com.bulatmain.authorization.config;

import com.bulatmain.authorization.application.service.AuthorizationService;
import com.bulatmain.authorization.application.service.FactService;
import com.bulatmain.authorization.application.service.impl.AuthorizationServiceImpl;
import com.bulatmain.authorization.application.service.impl.FactServiceImpl;
import com.osohq.oso_cloud.Oso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    AuthorizationService authorizationService(Oso oso) {
        return new AuthorizationServiceImpl(oso);
    }

    @Bean
    FactService factService(Oso oso) {
        return new FactServiceImpl(oso);
    }
}
