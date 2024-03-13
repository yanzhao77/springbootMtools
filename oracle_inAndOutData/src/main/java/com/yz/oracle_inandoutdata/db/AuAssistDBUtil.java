package com.yz.oracle_inandoutdata.db;


import com.yz.oracle_inandoutdata.entity.ConstUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.nio.file.Path;

public class AuAssistDBUtil  {

    private JdbcTemplate jdbcTemplate;

    public AuAssistDBUtil() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:" + Path.of(System.getenv(ConstUtil.ENV_BATCH_ROOT_PATH), "test", "lib", "db", "dataInfos.db"));
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    


}
