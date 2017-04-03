package com.codegans.jdbc.test;

import javax.sql.DataSource;
import java.sql.Timestamp;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 02/04/2017 17:40
 */
public class DstUtcTimestampTest extends UtcTimestampTest {
    public DstUtcTimestampTest(DataSource dataSource, String tableName, String columnName) {
        super(dataSource, tableName, columnName, new Timestamp(1490495400000L), "CET");
    }
}
