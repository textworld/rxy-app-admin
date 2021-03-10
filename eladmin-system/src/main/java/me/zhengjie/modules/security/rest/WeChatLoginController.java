package me.zhengjie.modules.security.rest;

import cn.hutool.http.server.HttpServerRequest;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.annotation.AnonymousAccess;
import me.zhengjie.modules.security.service.WeixinService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/wx/")
@RequiredArgsConstructor
@Api(tags = "系统：小程序登录")
public class WeChatLoginController {
    private final WeixinService weixinService;

    @AnonymousAccess
    @GetMapping(value = "/code")
    public String authCode2Session(HttpServletResponse response, String code) {
        weixinService.fetchOpenIdFromRemote(code);
        return code;
    }
}
