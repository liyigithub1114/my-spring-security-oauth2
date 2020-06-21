package test.liyi.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final ClientDetailsService clientDetailsService;

    private final TokenStore tokenStore;

    //private final AuthenticationManager authenticationManagerBean;


    public AuthorizationServerConfiguration(ClientDetailsService clientDetailsService,
                                            TokenStore tokenStore) {
        this.clientDetailsService = clientDetailsService;
        this.tokenStore = tokenStore;
        //this.authenticationManagerBean = authenticationManagerBean;
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore);
        endpoints.setClientDetailsService(clientDetailsService);
        //endpoints.authenticationManager(authenticationManagerBean);
    }
}
