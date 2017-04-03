package com.codegans.jdbc.action;

import com.codegans.jdbc.StoreAction;

import java.sql.PreparedStatement;
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
public class ZonedTimestampStore implements StoreAction<Timestamp> {
    private final TimeZone zone;

    public ZonedTimestampStore(String zone) {
        this(TimeZone.getTimeZone(zone));
    }

    public ZonedTimestampStore(TimeZone zone) {
        this.zone = zone;
    }

    @Override
    public void store(PreparedStatement ps, Timestamp value) throws SQLException {
        ps.setTimestamp(1, value, Calendar.getInstance(zone));
    }
}
