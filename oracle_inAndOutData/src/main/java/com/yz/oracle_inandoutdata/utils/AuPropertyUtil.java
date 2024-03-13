package com.yz.oracle_inandoutdata.utils;


import com.yz.oracle_inandoutdata.base.AuBase;
import com.yz.oracle_inandoutdata.entity.ConstUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class AuPropertyUtil extends AuBase {

    private static Properties properties;

    /**
     * @return
     */
    public static Properties load() {
        try {
            if (properties == null) {
                properties = new Properties();
                properties.load(Files.newBufferedReader(
                        Path.of(System.getenv(ConstUtil.ENV_BATCH_ROOT_PATH), "test", "config",
                                "application.properties"),
                        ConstUtil.ENCODING_SYSTEM));
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * @param key
     * @return
     */
    public static boolean getBoolean(String key) {
        return Boolean.valueOf(load().getProperty(key));
    }
    
    public static int getInt(String key) {
        return Integer.parseInt(load().getProperty(key));
    }

    /**
     * @param key
     * @return
     */
    public static String getString(String key) {
        return load().getProperty(key);
    }
}
