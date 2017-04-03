package com.codegans.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 02/04/2017 16:22
 */
public interface LoadAction<V> {
    V load(ResultSet rs) throws SQLException;
}
