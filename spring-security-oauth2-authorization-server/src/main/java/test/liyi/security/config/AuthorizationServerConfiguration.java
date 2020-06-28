package test.liyi.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final ClientDetailsService clientDetailsService;

    private final TokenStore tokenStore;

    private final AuthenticationManager authenticationManager;

    private final JwtAccessTokenConverter jwtAccessTokenConverter;

    public AuthorizationServerConfiguration(AuthenticationConfiguration authenticationConfiguration,
                                            ClientDetailsService clientDetailsService,
                                            TokenStore tokenStore,
                                            JwtAccessTokenConverter jwtAccessTokenConverter) throws Exception {
        this.clientDetailsService = clientDetailsService;
        this.tokenStore = tokenStore;
        this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore);
        endpoints.accessTokenConverter(jwtAccessTokenConverter);
        endpoints.authenticationManager(authenticationManager);
        endpoints.setClientDetailsService(clientDetailsService);
    }

}
