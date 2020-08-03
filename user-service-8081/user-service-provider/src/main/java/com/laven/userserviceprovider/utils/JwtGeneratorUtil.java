package com.laven.userserviceprovider.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Map;

@Slf4j
public class JwtGeneratorUtil {
    private static String SECRET_KEY = "laven";

    /**
     * 生产具体的token
     * @param payLoad
     * @return
     */
    public static String generaatorToken(Map<String , Object> payLoad) {
        ObjectMapper objectMapper = new ObjectMapper();
        String token = null;
        try {
            token = Jwts.builder().setPayload(objectMapper.writeValueAsString(payLoad))
                    .signWith(SignatureAlgorithm.HS256, getKeyInstance()).compact();
        } catch (JsonProcessingException e) {
            log.error("generatorToken:" + e);
        }
        return token;
    }

    private static Key getKeyInstance() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] keySecreteByte = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key key = new SecretKeySpec(keySecreteByte, signatureAlgorithm.getJcaName());
        return key;
    }

    /**
     * 根据token 获取token中的内容
     * @param token
     * @return
     */
    public static Claims parseToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(token);
        return claimsJws.getBody();
    }
}
