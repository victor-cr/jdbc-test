package com.codegans.jdbc.action;

import com.codegans.jdbc.StoreAction;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 02/04/2017 15:54
 */
public class LocalTimestampStore implements StoreAction<Timestamp> {
    @Override
    public void store(PreparedStatement ps, Timestamp value) throws SQLException {
        ps.setTimestamp(1, value);
    }
}
