package com.yz.oracle_inandoutdata.utils;


import com.yz.oracle_inandoutdata.base.AuBase;
import com.yz.oracle_inandoutdata.entity.ConstUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class AuLibPropertyUtil extends AuBase {

    private static Properties properties;

    /**
     * @return
     */
    public static Properties load() {
        try {
            if (properties == null) {
                properties = new Properties();
                properties.load(Files.newBufferedReader(
                        Path.of(System.getenv(ConstUtil.ENV_BATCH_ROOT_PATH), "config",
                                ConstUtil.CONFIG_LIBMANAGER),
                        ConstUtil.ENCODING_SYSTEM));
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return properties;
    }

    public static String getLibList(String libId) {
        String libs = load().getProperty(libId);
        if (libs == null) {
            logger.error("操作ユーザ({})に対するLIBの情報を取得できないので、確認してください。", libId);
            return null;
        }
        return libs.split("/")[0];
    }
}
