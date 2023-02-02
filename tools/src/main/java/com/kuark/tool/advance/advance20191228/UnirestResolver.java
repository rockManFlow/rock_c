package com.kuark.tool.advance.advance20191228;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.UnirestInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 看源码内部也是对httpclient的封装，使用的连接池也是httpclient连接池
 */
@Slf4j
public class UnirestResolver {

    @Bean
    public UnirestInstance unirest() {
        UnirestInstance unirest = Unirest.primaryInstance();
        unirest.config()
                .socketTimeout(10000)
                .connectTimeout(5000)
                //设置连接池最大个数
                .concurrency(1000, 200)
                .followRedirects(false)
                .enableCookieManagement(false)
                .automaticRetries(false)
                .addShutdownHook(true)
                .instrumentWith(requestSummary -> {
                    long startTime = System.currentTimeMillis();
                    return (responseSummary, exception) -> {
                        log.info("path: {} status: {} time: {}",
                                requestSummary.getRawPath(),
                                responseSummary!=null?responseSummary.getStatus():null,
                                System.currentTimeMillis() - startTime);
                    };
                });
        return unirest;
    }

    private static final String JSON_TYPE = "application/json; charset=utf-8";
    private static final String ENCODED_FORM = "application/x-www-form-urlencoded; charset=utf-8";

    @Resource
    private UnirestInstance unirest;


    public String getWithQuery(String url, Map<String, Object> params) throws UnirestException {
        return unirest.get(url)
                .header("Content-Type", ENCODED_FORM)
                .queryString(params)
                .asString()
                .getBody();

    }

    public String postJson(String url, String json) throws UnirestException {
        HttpResponse<String> resp = unirest.post(url)
                .header("Content-Type", JSON_TYPE)
                .body(json)
                .asString();
        if(resp!=null){
            return resp.getBody();
        }
        return null;
    }
}
