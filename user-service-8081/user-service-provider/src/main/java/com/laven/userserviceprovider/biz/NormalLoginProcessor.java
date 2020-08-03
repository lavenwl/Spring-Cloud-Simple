package com.laven.userserviceprovider.biz;

import com.laven.exception.BizException;
import com.laven.exception.ValidException;
import com.laven.userserviceprovider.controller.dto.AuthLoginDto;
import com.laven.userserviceprovider.controller.enums.LoginTypeEnum;
import com.laven.userserviceprovider.mapper.entitys.TbMember;
import com.laven.userserviceprovider.mapper.entitys.TbMemberExample;
import com.laven.userserviceprovider.mapper.persistence.TbMemberMapper;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Slf4j
@Service
public class NormalLoginProcessor extends AbstractLogin {

    @Autowired
    TbMemberMapper tbMemberMapper;

    @Override
    protected void validate(AuthLoginDto authLoginDto) {
        if (StringUtils.isBlank(authLoginDto.getUsername()) || StringUtils.isBlank(authLoginDto.getPassword())) {
            throw new ValidException("账号或密码不能为空");
        }
    }

    @Override
    protected TbMember doProcessor(AuthLoginDto authLoginDto) {
        log.info("begin NormalLoginProcessor.doProcessor:" + authLoginDto);
        TbMemberExample tbMemberExample = new TbMemberExample();
        tbMemberExample.createCriteria().andStateEqualTo(1)
                .andUsernameEqualTo(authLoginDto.getUsername());
        List<TbMember> memberList = tbMemberMapper.selectByExample(tbMemberExample);
        if (memberList == null || memberList.size() == 0) {
            throw new BizException("用户名或者密码错误");
        }
        if (!DigestUtils.md5DigestAsHex(authLoginDto.getPassword().getBytes()).equals(memberList.get(0).getPassword())) {
            throw  new BizException("用户名或密码错误");
        }
        return memberList.get(0);
    }

    @Override
    public int getLoginType() {
        return LoginTypeEnum.NORMAL.getCode();
    }
}
