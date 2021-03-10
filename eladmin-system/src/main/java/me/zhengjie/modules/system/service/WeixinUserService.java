package me.zhengjie.modules.system.service;

import me.zhengjie.modules.system.domain.User;
import me.zhengjie.modules.system.domain.WeixinUser;

public interface WeixinUserService {
    /**
     * 新增用户
     * @param resources /
     */
    WeixinUser create(String openId);

}
