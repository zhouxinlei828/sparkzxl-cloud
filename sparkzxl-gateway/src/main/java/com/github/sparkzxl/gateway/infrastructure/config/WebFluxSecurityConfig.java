package com.github.sparkzxl.gateway.infrastructure.config;

import com.github.sparkzxl.gateway.infrastructure.authorization.AuthorizationManager;
import com.github.sparkzxl.gateway.infrastructure.components.MyServerAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.time.Duration;

@Configuration
@EnableWebFluxSecurity
public class WebFluxSecurityConfig {

    @Autowired
    private MyServerAuthenticationSuccessHandler myServerAuthenticationSuccessHandler;

    @Autowired
    private AuthorizationManager authorizationManager;

    private static final Duration accessTokenExpiresSkew = Duration.ofHours(3);

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http,
                                                            ReactiveClientRegistrationRepository clientRegistrationRepository) {
        http.cors();
        http.oauth2Login().authenticationSuccessHandler(myServerAuthenticationSuccessHandler);
        http.logout(logout -> logout.logoutSuccessHandler(
                new OidcClientInitiatedServerLogoutSuccessHandler(clientRegistrationRepository)));
        http.logout().logoutUrl("/auth/logout");
        http.authorizeExchange().anyExchange().authenticated();
        http.oauth2ResourceServer().jwt();
        http.headers().frameOptions().disable().xssProtection().disable();
        http.csrf().disable();
        http.httpBasic().disable();
        http.formLogin().disable();
        return http.build();
    }

    @Bean
    public ReactiveOAuth2AuthorizedClientManager authorizedClientManager(ServerOAuth2AuthorizedClientRepository authorizedClientRepository,
                                                                         ReactiveClientRegistrationRepository clientRegistrationRepository) {
        final ReactiveOAuth2AuthorizedClientProvider authorizedClientProvider =
                ReactiveOAuth2AuthorizedClientProviderBuilder.builder()
                        .authorizationCode()
                        .refreshToken(configurer -> configurer.clockSkew(accessTokenExpiresSkew))
                        .clientCredentials(configurer -> configurer.clockSkew(accessTokenExpiresSkew))
                        .password(configurer -> configurer.clockSkew(accessTokenExpiresSkew))
                        .build();
        final DefaultReactiveOAuth2AuthorizedClientManager authorizedClientManager = new DefaultReactiveOAuth2AuthorizedClientManager(
                clientRegistrationRepository, authorizedClientRepository);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
        return authorizedClientManager;
    }
}
