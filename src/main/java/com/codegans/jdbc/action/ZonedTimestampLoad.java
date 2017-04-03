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
public class ZonedTimestampLoad implements LoadAction<Timestamp> {
    private final TimeZone zone;

    public ZonedTimestampLoad(String zone) {
        this(TimeZone.getTimeZone(zone));
    }

    public ZonedTimestampLoad(TimeZone zone) {
        this.zone = zone;
    }

    @Override
    public Timestamp load(ResultSet rs) throws SQLException {
        return rs.getTimestamp(1, Calendar.getInstance(zone));
    }
}
