package com.codegans.jdbc.action;

import com.codegans.jdbc.LoadAction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 02/04/2017 15:54
 */
public class LocalTimestampLoad implements LoadAction<Timestamp> {
    @Override
    public Timestamp load(ResultSet rs) throws SQLException {
        return rs.getTimestamp(1);
    }
}
