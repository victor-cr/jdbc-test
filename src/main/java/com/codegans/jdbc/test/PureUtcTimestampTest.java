package com.codegans.jdbc.test;

import javax.sql.DataSource;
import java.sql.Timestamp;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 02/04/2017 17:40
 */
public class PureUtcTimestampTest extends UtcTimestampTest {
    public PureUtcTimestampTest(DataSource dataSource, String tableName, String columnName) {
        super(dataSource, tableName, columnName, new Timestamp(1490495400000L), "UTC");
    }
}
