package me.zhengjie;

import me.zhengjie.modules.system.domain.WeixinUser;
import me.zhengjie.modules.system.service.WeixinUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EladminSystemApplicationTests {

    @Autowired
    WeixinUserService weixinUserService;

    @Test
    public void contextLoads() {
        WeixinUser user = weixinUserService.create("tttt");
        Assert.assertTrue(user != null);
    }

    public static void main(String[] args) {

    }
}

