

package com.wanniwa.king.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wanniwa.king.common.security.component.KingOauthExceptionSerializer;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author wanniwa
 * 自定义OAuth2Exception
 */
@JsonSerialize(using = KingOauthExceptionSerializer.class)
public class KingOAuthException extends OAuth2Exception {
	@Getter
	private int code;

	public KingOAuthException(Integer code,String msg) {
		super(msg);
		this.code = code;
	}
}
