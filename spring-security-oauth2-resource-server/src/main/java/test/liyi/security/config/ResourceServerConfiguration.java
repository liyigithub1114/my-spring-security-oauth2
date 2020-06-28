package test.liyi.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

//OAuth2AuthenticationProcessingFilter
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private final JwtDecoder jwtDecoder;

    private final ResourceServerTokenServices resourceServerTokenServices;

    public ResourceServerConfiguration(JwtDecoder jwtDecoder,
                                       ResourceServerTokenServices resourceServerTokenServices) {
        this.jwtDecoder = jwtDecoder;
        this.resourceServerTokenServices = resourceServerTokenServices;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/resources/**").hasAnyAuthority("ROLE_USER","USER")
            .and()
            .csrf().disable()
            .oauth2ResourceServer().jwt().decoder(jwtDecoder);
        super.configure(http);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
        resources.tokenServices(resourceServerTokenServices);
        resources.resourceId("1");
    }

}
