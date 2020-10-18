package com.wanniwa.king.common.security.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;

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
