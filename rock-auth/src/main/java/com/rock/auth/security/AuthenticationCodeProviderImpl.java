package com.rock.auth.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service(value = "authenticationCodeProvider")
@Slf4j
public class AuthenticationCodeProviderImpl implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("AuthenticationCode authenticate");
        String username = (String) authentication.getPrincipal();
        String code = (String)authentication.getCredentials();

        //校验code
        //校验通过返回AuthCodeAuthenticationToken，否则抛出异常
        if("test1".equals(code)){
            return new AuthCodeAuthenticationToken(username,code);
        }
        throw new RuntimeException("code authenticate fail");
    }

    /**
     * 验证逻辑支持的token类型
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(AuthCodeAuthenticationToken.class);
    }
}
