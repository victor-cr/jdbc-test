package com.codegans.jdbc;

import com.codegans.jdbc.test.DstUtcTimestampTest;
import com.codegans.jdbc.test.NormalUtcTimestampTest;
import com.codegans.jdbc.test.PureUtcTimestampTest;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * Hello world!
 */
public class Runner {
    private static final String DRIVER_CLASS = "oracle.jdbc.OracleDriver";
    private static final String DRIVER_URL = "<JDBC URL>";
    private static final String DRIVER_USER = "<USER NAME>";
    private static final String DRIVER_PASS = "<PASSWORD>";
    private static final DataSource DS = new SimpleDataSource(DRIVER_URL, DRIVER_USER, DRIVER_PASS);

    private static final Test[] SUITE = new Test[]{
            new DstUtcTimestampTest(DS, "TMP_DATE", "FIELD_TIMESTAMP"),
            new DstUtcTimestampTest(DS, "TMP_DATE", "FIELD_TIMESTAMPTZ"),
            new NormalUtcTimestampTest(DS, "TMP_DATE", "FIELD_TIMESTAMP"),
            new NormalUtcTimestampTest(DS, "TMP_DATE", "FIELD_TIMESTAMPTZ"),
            new PureUtcTimestampTest(DS, "TMP_DATE", "FIELD_TIMESTAMP"),
            new PureUtcTimestampTest(DS, "TMP_DATE", "FIELD_TIMESTAMPTZ")
    };

    static {
        try {
            DriverManager.registerDriver((Driver) Class.forName(DRIVER_CLASS).newInstance());
        } catch (Exception e) {
            throw new IllegalStateException("Cannot find/instantiate proper JDBC driver: " + DRIVER_CLASS);
        }
    }

    public static void main(String[] args) {
        System.out.println("====================================================================");
        System.out.println("START tests");
        System.out.println("====================================================================");

        for (Test test : SUITE) {
            try {
                System.out.println(test.name() + ": " + test.run());
            } catch (Exception e) {
                System.out.println(test.name() + ": FAILED... " + trim(e.getMessage()));
            } catch (AssertionError e) {
                System.out.println(test.name() + ": ERROR... " + trim(e.getMessage()));
            }
        }

        System.out.println("====================================================================");
        System.out.println("END tests");
        System.out.println("====================================================================");
    }

    private static String trim(String message) {
        if (message == null) {
            return null;
        }

        return message.replace('\r', '\n').replaceAll("\n\n", "\n").replace('\n', '.');
    }

    private static class SimpleDataSource implements DataSource {
        private final String url;
        private final String username;
        private final String password;

        SimpleDataSource(String url, String username, String password) {
            this.url = url;
            this.username = username;
            this.password = password;
        }

        @Override
        public Connection getConnection() throws SQLException {
            Connection c = DriverManager.getConnection(url, username, password);

            c.setAutoCommit(true);

            return c;
        }

        @Override
        public Connection getConnection(String username, String password) throws SQLException {
            Connection c = DriverManager.getConnection(url, username, password);

            c.setAutoCommit(true);

            return c;
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            throw new NotImplementedException();
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            throw new NotImplementedException();
        }

        @Override
        public PrintWriter getLogWriter() throws SQLException {
            return new PrintWriter(System.out);
        }

        @Override
        public void setLogWriter(PrintWriter out) throws SQLException {
            throw new NotImplementedException();
        }

        @Override
        public void setLoginTimeout(int seconds) throws SQLException {
            throw new NotImplementedException();
        }

        @Override
        public int getLoginTimeout() throws SQLException {
            throw new NotImplementedException();
        }

        public Logger getParentLogger() throws SQLFeatureNotSupportedException {
            throw new NotImplementedException();
        }
    }
}
