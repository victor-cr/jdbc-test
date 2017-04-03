package com.codegans.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 03/04/2017 13:57
 */
public enum Param {
    DRIVER_CLASS("driver.class"),
    DRIVER_URL("driver.url"),
    DRIVER_USERNAME("driver.username"),
    DRIVER_PASSWORD("driver.password"),

    TABLE_NAME("table.name"),
    FIELD_DATE("table.datetime.short"),
    FIELD_TIMESTAMP("table.datetime.long"),
    FIELD_ZONED("table.datetime.zoned"),
    FIELD_LOCAL("table.datetime.local");

    private static final Properties PROPS = new Properties();

    static {
        try {
            File external = new File(System.getProperty("user.home"), "jdbc-test.properties");

            InputStream in;
            
            if (external.isFile()) {
                in = new FileInputStream(external);
            } else {
                in = Param.class.getClassLoader().getResourceAsStream("jdbc-test.properties");
            }

            try {
                PROPS.load(in);
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new IllegalStateException("Cannot find/parse the configuration file");
        }
    }

    private final String key;

    Param(String key) {
        this.key = key;
    }

    public String get() {
        return PROPS.getProperty(key);
    }
}
