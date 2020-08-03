package com.laven.userserviceprovider.controller;

import com.laven.api.R;
import com.laven.exception.BizException;
import com.laven.userserviceprovider.biz.AbstractLogin;
import com.laven.userserviceprovider.biz.Login;
import com.laven.userserviceprovider.controller.dto.AuthLoginDto;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/login")
    public R login(@RequestBody @Validated AuthLoginDto authLoginDto, BindingResult bindingResult) {
        authLoginDto.validData(bindingResult);

        Login login = AbstractLogin.loginConcurrentHashMap.get(authLoginDto.getLoginType());
        if (login == null) {
            throw new BizException("暂不支持的登录方式");
        }
        return login.doLogin(authLoginDto);
    }
}
