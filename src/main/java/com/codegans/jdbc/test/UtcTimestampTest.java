package com.codegans.jdbc.test;

import com.codegans.jdbc.Test;
import com.codegans.jdbc.check.Check;
import com.codegans.jdbc.check.Utc2UtcTimestampCheck;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 02/04/2017 17:40
 */
public abstract class UtcTimestampTest implements Test {
    private final Check<Timestamp> check;
    private final Timestamp value;
    private final String tableName;
    private final String columnName;
    private final String defaultTimeZoneId;
    private final TimeZone defaultTimeZone;

    UtcTimestampTest(DataSource dataSource, String tableName, String columnName, Timestamp value, String defaultTimeZoneId) {
        this.check = new Utc2UtcTimestampCheck(dataSource, tableName, columnName);
        
        this.tableName = tableName;
        this.columnName = columnName;
        this.value = value;
        this.defaultTimeZoneId = defaultTimeZoneId;
        this.defaultTimeZone = TimeZone.getTimeZone(defaultTimeZoneId);
    }

    @Override
    public String name() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        format.setTimeZone(defaultTimeZone);

        return "Store/load UTC (default " + defaultTimeZoneId + ") [" + tableName + "." + columnName + "] with " + format.format(value);
    }

    @Override
    public String run() throws Exception {
        TimeZone tz = TimeZone.getDefault();

        try {
            TimeZone.setDefault(defaultTimeZone);

            return check.run(value);
        } finally {
            TimeZone.setDefault(tz);
        }
    }
}
