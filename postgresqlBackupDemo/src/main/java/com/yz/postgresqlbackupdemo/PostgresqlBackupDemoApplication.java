package com.yz.postgresqlbackupdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.yz.postgresqlbackupdemo.mapper")
@SpringBootApplication
public class PostgresqlBackupDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostgresqlBackupDemoApplication.class, args);
    }

}
