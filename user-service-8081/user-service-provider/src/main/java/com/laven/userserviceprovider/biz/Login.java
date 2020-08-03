package com.laven.userserviceprovider.biz;

import com.laven.api.R;
import com.laven.exception.BizException;
import com.laven.userserviceprovider.controller.dto.AuthLoginDto;

public interface Login {
    R doLogin(AuthLoginDto authLoginDto) throws BizException;
}
