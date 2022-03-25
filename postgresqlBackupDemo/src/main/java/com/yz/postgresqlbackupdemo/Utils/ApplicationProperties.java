package com.yz.postgresqlbackupdemo.Utils;
 
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
 
@Component
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {
 
    private ServerConfig serverConfig = new ServerConfig();
 
    public ServerConfig getServerConfig() {
        return serverConfig;
    }
    public final class ServerConfig{
        private String address;
        private String port;
        private String username;
        private String password;
        // geter/setter 此处略
    }
 
}