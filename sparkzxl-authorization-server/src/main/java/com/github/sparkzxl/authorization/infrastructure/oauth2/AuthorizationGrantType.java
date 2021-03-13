package com.github.sparkzxl.authorization.infrastructure.oauth2;

import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * description: oauth2.0授权类型
 *
 * @author: zhouxinlei
 * @date: 2021-03-13 09:36:55
 */
public final class AuthorizationGrantType implements Serializable {

    public static final AuthorizationGrantType AUTHORIZATION_CODE = new AuthorizationGrantType("authorization_code");
    public static final AuthorizationGrantType IMPLICIT = new AuthorizationGrantType("implicit");
    public static final AuthorizationGrantType REFRESH_TOKEN = new AuthorizationGrantType("refresh_token");
    public static final AuthorizationGrantType CLIENT_CREDENTIALS = new AuthorizationGrantType("client_credentials");
    public static final AuthorizationGrantType PASSWORD = new AuthorizationGrantType("password");
    private final String value;

    public AuthorizationGrantType(String value) {
        Assert.hasText(value, "value cannot be empty");
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj != null && this.getClass() == obj.getClass()) {
            AuthorizationGrantType that = (AuthorizationGrantType) obj;
            return this.getValue().equals(that.getValue());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getValue().hashCode();
    }
}
