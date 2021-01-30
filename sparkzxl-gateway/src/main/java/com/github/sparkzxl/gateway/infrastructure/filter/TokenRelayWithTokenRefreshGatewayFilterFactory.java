package com.github.sparkzxl.gateway.infrastructure.filter;

import com.github.sparkzxl.core.context.BaseContextConstants;
import com.github.sparkzxl.core.entity.AuthUserInfo;
import com.github.sparkzxl.gateway.infrastructure.handler.AuthenticationTokenHandler;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * description:
 *
 * @author: zhouxinlei
 * @date: 2021-01-29 14:22:43
 */
@Component
public class TokenRelayWithTokenRefreshGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    private final ReactiveOAuth2AuthorizedClientManager authorizedClientManager;

    private static final Duration accessTokenExpiresSkew = Duration.ofHours(3);

    @Autowired
    private AuthenticationTokenHandler authenticationTokenHandler;

    public TokenRelayWithTokenRefreshGatewayFilterFactory(ServerOAuth2AuthorizedClientRepository authorizedClientRepository,
                                                          ReactiveClientRegistrationRepository clientRegistrationRepository) {
        super(Object.class);
        this.authorizedClientManager = createDefaultAuthorizedClientManager(clientRegistrationRepository, authorizedClientRepository);
    }

    private static ReactiveOAuth2AuthorizedClientManager createDefaultAuthorizedClientManager(
            ReactiveClientRegistrationRepository clientRegistrationRepository,
            ServerOAuth2AuthorizedClientRepository authorizedClientRepository) {

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

    public GatewayFilter apply() {
        return apply((Object) null);
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> exchange.getPrincipal()
                // .log("token-relay-filter")
                .filter(principal -> principal instanceof OAuth2AuthenticationToken)
                .cast(OAuth2AuthenticationToken.class)
                .flatMap(this::authorizeClient)
                .map(OAuth2AuthorizedClient::getAccessToken)
                .map(token -> withBearerAuth(exchange, token))
                // TODO: adjustable behavior if empty
                .defaultIfEmpty(exchange).flatMap(chain::filter);
    }

    private ServerWebExchange withBearerAuth(ServerWebExchange exchange, OAuth2AccessToken accessToken) {
        AuthUserInfo<String> authUserInfo = authenticationTokenHandler.buildAuthUserInfoCache(accessToken.getTokenValue(),
                accessToken.getExpiresAt());
        String username = authUserInfo.getName();
        String account = authUserInfo.getAccount();
        String sub = authUserInfo.getId();
        MDC.put(BaseContextConstants.JWT_KEY_USER_ID, sub);
        return exchange.mutate().request(r -> r.header(BaseContextConstants.JWT_TOKEN_HEADER, accessToken.getTokenValue())
                .header(BaseContextConstants.JWT_KEY_USER_ID, sub)
                .header(BaseContextConstants.KEYCLOAK_KEY, BaseContextConstants.KEYCLOAK_KEY)
                .header(BaseContextConstants.JWT_KEY_ACCOUNT, account)
                .header(BaseContextConstants.JWT_KEY_NAME, username)).build();
    }

    private Mono<OAuth2AuthorizedClient> authorizeClient(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        final String clientRegistrationId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
        return Mono.defer(() -> authorizedClientManager.authorize(createOauth2AuthorizeRequest(clientRegistrationId, oAuth2AuthenticationToken)));
    }

    private OAuth2AuthorizeRequest createOauth2AuthorizeRequest(String clientRegistrationId, Authentication principal) {
        return OAuth2AuthorizeRequest.withClientRegistrationId(clientRegistrationId).principal(principal).build();
    }
}
