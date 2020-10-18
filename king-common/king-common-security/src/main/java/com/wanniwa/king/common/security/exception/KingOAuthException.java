package com.wanniwa.king.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 自定义OAuth2Exception
 *
 * @author wanniwa
 */
@Getter
@JsonSerialize(using = KingOauthExceptionSerializer.class)
public class KingOAuthException extends OAuth2Exception {

    private final int httpErrorCode;
    private final String oAuth2ErrorCode;


    public KingOAuthException(String msg, Throwable t, String oAuth2ErrorCode, int httpErrorCode) {
        super(msg, t);
        this.oAuth2ErrorCode = oAuth2ErrorCode;
        this.httpErrorCode = httpErrorCode;
    }

}


