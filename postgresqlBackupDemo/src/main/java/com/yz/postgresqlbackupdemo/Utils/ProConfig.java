package com.yz.postgresqlbackupdemo.Utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author yanzhao
 * @version 1.0
 * TODO
 * @date 2022/3/22 19:47
 */

@Component
public class ProConfig {

    @Value("spring.datasource.url")
    public String url;

    @Value("spring.datasource.username")
    public String username;

    @Value("spring.datasource.password")
    public String pwd;
}
