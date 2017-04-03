package com.codegans.jdbc.check;

import com.codegans.jdbc.JdbcStoreLoad;
import com.codegans.jdbc.action.UtcTimestampLoad;
import com.codegans.jdbc.action.UtcTimestampStore;

import javax.sql.DataSource;
import java.sql.Timestamp;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 02/04/2017 17:00
 */
public class Utc2UtcTimestampCheck implements Check<Timestamp> {
    private static final int MILLIS_HOUR = 1000 * 60 * 60;
    private final JdbcStoreLoad<Timestamp> jdbcStoreLoad;

    public Utc2UtcTimestampCheck(DataSource dataSource, String tableName, String columnName) {
        jdbcStoreLoad = new JdbcStoreLoad<Timestamp>(dataSource, tableName, columnName, new UtcTimestampStore(), new UtcTimestampLoad());
    }

    @Override
    public String run(Timestamp value) throws Exception {
        jdbcStoreLoad.cleanup();

        jdbcStoreLoad.save(value);

        Timestamp result = jdbcStoreLoad.load();

        long expected = value.getTime();
        long actual = result.getTime();

        if (expected == actual) {
            return "OK...";
        }

        return "FAILED... Expected: " + value + ", but was: " + result + " [difference: " + ((double) (expected - actual)) / MILLIS_HOUR + " hours]";
    }
}
