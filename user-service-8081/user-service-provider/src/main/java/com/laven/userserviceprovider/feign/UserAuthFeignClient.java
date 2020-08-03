package com.laven.userserviceprovider.feign;

import com.laven.api.R;
import com.laven.clients.IUserAuthFeignClient;
import com.laven.exception.ValidException;
import com.laven.userserviceprovider.utils.JwtGeneratorUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthFeignClient implements IUserAuthFeignClient {

    @Override
    public R<String> validToken(String token) {
        if (StringUtils.isBlank(token)) {
            throw new ValidException("token 不能为空");
        }

        try {
            Claims claims = JwtGeneratorUtil.parseToken(token);
            return new R.Builder().setData(claims.get("uid").toString()).buildSuccess();
        } catch (ExpiredJwtException e) {
            return new R.Builder().buildFailed("token 已过期");
        } catch (SignatureException e) {
            return new R.Builder().buildFailed("签名校验失败");
        }
    }
}
