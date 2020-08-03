package com.laven.userserviceprovider.biz;

import com.laven.api.R;
import com.laven.exception.BizException;
import com.laven.userserviceprovider.controller.dto.AuthLoginDto;
import com.laven.userserviceprovider.mapper.entitys.TbMember;
import com.laven.userserviceprovider.utils.JwtGeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public abstract class AbstractLogin implements Login{

    public static ConcurrentHashMap<Integer, AbstractLogin> loginConcurrentHashMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        loginConcurrentHashMap.put(getLoginType(), this);
    }

    @Override
    public R doLogin(AuthLoginDto authLoginDto) throws BizException {
        log.info("begin AbstractLogin.doLogin:" + authLoginDto);
        validate(authLoginDto);

        TbMember member = doProcessor(authLoginDto);

        Map<String, Object> payLoad = new HashMap<>();
        payLoad.put("uid", member.getId());
        payLoad.put("exp", DateTime.now().plusHours(1).toDate().getTime() / 1000);
        String token = JwtGeneratorUtil.generaatorToken(payLoad);
        return new R.Builder().setData(token).buildSuccess();
    }

    /**
     * 通过子类完成验证
     * @param authLoginDto
     */
    protected abstract void validate(AuthLoginDto authLoginDto);

    /**
     * 具体登录校验逻辑
     * @param authLoginDto
     * @return
     */
    protected abstract TbMember doProcessor(AuthLoginDto authLoginDto);

    /**
     * 具体子类中声明自己的登录类型
     * @return
     */
    public abstract int getLoginType();

}
