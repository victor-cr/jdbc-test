package com.codegans.jdbc.action;

import com.codegans.jdbc.LoadAction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 02/04/2017 15:54
 */
public class UtcTimestampLoad implements LoadAction<Timestamp> {
    private static final TimeZone UTC = TimeZone.getTimeZone("UTC");

    @Override
    public Timestamp load(ResultSet rs) throws SQLException {
        return rs.getTimestamp(1, Calendar.getInstance(UTC));
    }
}
