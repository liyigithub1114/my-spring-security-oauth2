package test.liyi.security.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

@Configuration
public class ServiceConfiguration {

    @Bean
    public ClientDetailsService clientDetailsService(HikariDataSource dataSource) {
        return new JdbcClientDetailsService(dataSource);
    }

}
