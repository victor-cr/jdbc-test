package com.codegans.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 02/04/2017 15:54
 */
public final class JdbcStoreLoad<V> {
    private final DataSource dataSource;
    private final String tableName;
    private final String columnName;
    private final StoreAction<V> store;
    private final LoadAction<V> load;

    public JdbcStoreLoad(DataSource dataSource, String tableName, String columnName, StoreAction<V> store, LoadAction<V> load) {
        this.dataSource = dataSource;
        this.tableName = tableName;
        this.columnName = columnName;
        this.store = store;
        this.load = load;
    }

    public void cleanup() throws SQLException {
        Connection c = dataSource.getConnection();

        try {
            PreparedStatement ps = c.prepareStatement("delete from " + tableName);

            try {
                ps.execute();
            } finally {
                ps.close();
            }
        } finally {
            c.close();
        }
    }

    public void save(V value) throws SQLException {
        Connection c = dataSource.getConnection();

        try {
            PreparedStatement ps = c.prepareStatement("insert into " + tableName + " (" + columnName + ") values (?)");

            try {
                store.store(ps, value);

                ps.executeUpdate();
            } finally {
                ps.close();
            }
        } finally {
            c.close();
        }
    }

    public V load() throws SQLException {
        Connection c = dataSource.getConnection();

        try {
            PreparedStatement ps = c.prepareStatement("select " + columnName + " from " + tableName);

            try {
                ResultSet rs = ps.executeQuery();

                try {
                    if (rs.next()) {
                        return load.load(rs);
                    }

                    throw new AssertionError("No data. Should not be ever reachable.");
                } finally {
                    rs.close();
                }
            } finally {
                ps.close();
            }
        } finally {
            c.close();
        }
    }
}
