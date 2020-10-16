package com.wanniwa.king.common.security.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.io.IOException;

/**
 * DefaultWebResponseExceptionTranslator
 *
 * @author wanniwa
 */
@Slf4j
public class KingWebResponseExceptionTranslator extends DefaultWebResponseExceptionTranslator {

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
        OAuth2Exception body = responseEntity.getBody();
        assert body != null;
        KingOAuthException kingOAuthException = new KingOAuthException(body.getMessage(), body.getCause(), body.getOAuth2ErrorCode(), body.getHttpErrorCode());
        return new ResponseEntity<>(kingOAuthException, responseEntity.getHeaders(),
                responseEntity.getStatusCode());
    }
}
