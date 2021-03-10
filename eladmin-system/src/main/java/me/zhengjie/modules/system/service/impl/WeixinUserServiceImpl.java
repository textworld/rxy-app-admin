package me.zhengjie.modules.system.service.impl;

import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.domain.WeixinUser;
import me.zhengjie.modules.system.repository.UserRepository;
import me.zhengjie.modules.system.repository.WeixinUserRepository;
import me.zhengjie.modules.system.service.WeixinUserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "user")
public class WeixinUserServiceImpl implements WeixinUserService {
    private final WeixinUserRepository weixinUserRepository;

    @Override
    public WeixinUser create(String openId) {
        if (weixinUserRepository.findByOpenId(openId) != null) {

        }
        return null;
    }
}
