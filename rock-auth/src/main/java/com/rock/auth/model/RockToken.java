package com.rock.auth.model;

import lombok.Data;

/**
 * 认证通过之后的鉴权token
 *
 * 可以使用security的OAuth2包来生成token，
 *
 * JWT的方式生成定制化token
 */
@Data
public class RockToken {
    private String accessToken;

    private String tokenType;

    private String refreshToken;

    private String expiresIn;

    private String scope;

    /**
     * access_token：代表 OAuth2 的令牌，当访问每个受保护的资源时，用户都需要携带这个令牌以便进行验证。
     * token_type：代表令牌类型，OAuth2 协议中有多种可选的令牌类型，包括 Bearer 类型、MAC 类型等，这里指定的 Bearer 类型是最常见的一种类型。
     * expires_in：用于指定 access_token 的有效时间，当超过这个有效时间，access_token 将会自动失效。
     * refresh_token：其作用在于当 access_token 过期后，重新下发一个新的 access_token。
     * scope：指定了可访问的权限范围，这里指定的是访问 Web 资源的“webclient”。
     */
}
