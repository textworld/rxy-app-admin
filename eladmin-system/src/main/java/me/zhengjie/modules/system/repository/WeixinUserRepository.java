package me.zhengjie.modules.system.repository;

import me.zhengjie.modules.system.domain.User;
import me.zhengjie.modules.system.domain.WeixinUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WeixinUserRepository extends JpaRepository<WeixinUser, Long>, JpaSpecificationExecutor<WeixinUser> {
    WeixinUser findByOpenId(String openId);
}
