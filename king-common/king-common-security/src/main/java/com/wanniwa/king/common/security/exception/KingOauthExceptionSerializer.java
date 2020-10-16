package com.wanniwa.king.common.security.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.wanniwa.king.common.core.constant.CommonConstants;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class KingOauthExceptionSerializer extends StdSerializer<KingOAuthException> {

    public KingOauthExceptionSerializer() {
        super(KingOAuthException.class);
    }

    @Override
    public void serialize(KingOAuthException e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("code", CommonConstants.FAIL);
        jsonGenerator.writeStringField("msg", e.getMessage());
        jsonGenerator.writeStringField("data", e.getOAuth2ErrorCode());
        jsonGenerator.writeStringField("timestamp", String.valueOf(System.currentTimeMillis()));
        jsonGenerator.writeStringField("path", request.getServletPath());
        if (e.getAdditionalInformation()!=null) {
            for (Map.Entry<String, String> entry : e.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                jsonGenerator.writeStringField(key, add);
            }
        }
        jsonGenerator.writeEndObject();
    }
}
