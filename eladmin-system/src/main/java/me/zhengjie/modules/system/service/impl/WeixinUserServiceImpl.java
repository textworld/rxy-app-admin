package me.zhengjie.modules.system.service.impl;

import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.domain.User;
import me.zhengjie.modules.system.domain.WeixinUser;
import me.zhengjie.modules.system.repository.UserRepository;
import me.zhengjie.modules.system.repository.WeixinUserRepository;
import me.zhengjie.modules.system.service.UserService;
import me.zhengjie.modules.system.service.WeixinUserService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "user")
public class WeixinUserServiceImpl implements WeixinUserService {
    private final WeixinUserRepository weixinUserRepository;
    private final UserService userService;

    @Override
    @Transactional
    public WeixinUser create(String openId) {
        WeixinUser wxUser = weixinUserRepository.findByOpenId(openId);
        if (wxUser != null) {
            return wxUser;
        }else{
            User user = new User();
            String randomUsername = "rd_" + RandomStringUtils.randomAlphabetic(10);
            while (userService.findByName(randomUsername) != null) {
                randomUsername= "rd_" + RandomStringUtils.randomAlphabetic(10);
            }

            String randomPhone = "0" + RandomStringUtils.randomNumeric(10);
            user.setEmail(randomUsername + "@textworld.cn");
            user.setPassword(RandomStringUtils.randomAlphanumeric(10));
            user.setNickName(randomUsername);
            user.setUsername(randomUsername);
            user.setPhone(randomPhone);
            user.setEnabled(true);
            user.setIsAdmin(false);
            userService.create(user);

            WeixinUser weixinUser = new WeixinUser();
            weixinUser.setOpenId(openId);
            weixinUser.setUser(user);
            weixinUserRepository.save(weixinUser);
            return weixinUser;
        }
    }
}
