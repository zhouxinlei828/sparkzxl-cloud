package com.github.sparkzxl.gateway.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * description: jwt用户信息
 *
 * @author: zhouxinlei
 * @date: 2021-01-29 15:21:14
 */
@NoArgsConstructor
@Data
public class JwtAuthUserInfo {

    @JsonProperty("exp")
    private Integer exp;
    @JsonProperty("iat")
    private Integer iat;
    @JsonProperty("auth_time")
    private Integer authTime;
    @JsonProperty("jti")
    private String jti;
    @JsonProperty("iss")
    private String iss;
    @JsonProperty("aud")
    private String aud;
    @JsonProperty("sub")
    private String sub;
    @JsonProperty("typ")
    private String typ;
    @JsonProperty("azp")
    private String azp;
    @JsonProperty("nonce")
    private String nonce;
    @JsonProperty("session_state")
    private String sessionState;
    @JsonProperty("acr")
    private String acr;
    @JsonProperty("realm_access")
    private RealmAccessDTO realmAccess;
    @JsonProperty("resource_access")
    private ResourceAccessDTO resourceAccess;
    @JsonProperty("scope")
    private String scope;
    @JsonProperty("upn")
    private String upn;
    @JsonProperty("email_verified")
    private Boolean emailVerified;
    @JsonProperty("address")
    private AddressDTO address;
    @JsonProperty("name")
    private String name;
    @JsonProperty("preferred_username")
    private String preferredUsername;
    @JsonProperty("given_name")
    private String givenName;
    @JsonProperty("family_name")
    private String familyName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("allowed-origins")
    private List<String> allowedOrigins;
    @JsonProperty("groups")
    private List<String> groups;

    @NoArgsConstructor
    @Data
    public static class RealmAccessDTO {
        @JsonProperty("roles")
        private List<String> roles;
    }

    @NoArgsConstructor
    @Data
    public static class ResourceAccessDTO {

        @JsonProperty("account")
        private AccountDTO account;

        @NoArgsConstructor
        @Data
        public static class AccountDTO {
            @JsonProperty("roles")
            private List<String> roles;
        }
    }

    @NoArgsConstructor
    @Data
    public static class AddressDTO {
    }
}
