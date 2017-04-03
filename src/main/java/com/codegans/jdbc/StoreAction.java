package com.codegans.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 02/04/2017 16:22
 */
public interface StoreAction<V> {
    void store(PreparedStatement ps, V value) throws SQLException;
}
