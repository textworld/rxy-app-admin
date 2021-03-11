package me.zhengjie.modules.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.security.config.bean.SecurityProperties;
import me.zhengjie.modules.security.service.dto.WxSessionDTO;
import me.zhengjie.utils.RedisUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
@Slf4j
public class WeixinService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private SecurityProperties properties;

    @Value("${weixin.appid}")
    private String appid;

    @Value("${weixin.secret}")
    private String appsecret;

    public WxSessionDTO fetchOpenIdFromRemote(String code) throws IOException, URISyntaxException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        String url="https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+appsecret+"&js_code="+code+"&grant_type=authorization_code";

        URIBuilder builder = new URIBuilder(url);
        URI uri = builder.build();

        HttpGet httpGet = new HttpGet(uri);

        response = httpclient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            log.info("body: {}", resultString);

            WxSessionDTO dto = objectMapper.readValue(resultString, WxSessionDTO.class);
            if (dto.getErrcode() != null && dto.getErrcode() != 0) {
                log.error("invalid weixin session: {}", resultString);
                throw new IOException(dto.getErrmsg());
            }
            return dto;
        }else{
            throw new IOException("error status code: " + response.getStatusLine().getStatusCode());
        }
    }
}
