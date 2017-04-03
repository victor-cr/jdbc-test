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
    private static final Driver DRIVER;

    static {
        try {
            DRIVER = (Driver) Class.forName(Param.DRIVER_CLASS.get()).newInstance();

            DriverManager.registerDriver(DRIVER);
        } catch (Exception e) {
            throw new IllegalStateException("Cannot find/instantiate proper JDBC driver: " + Param.DRIVER_CLASS.get());
        }
    }

    private static final DataSource DS = new SimpleDataSource(Param.DRIVER_URL.get(), Param.DRIVER_USERNAME.get(), Param.DRIVER_PASSWORD.get());
    private static final Test[] SUITE = new Test[]{
            new DstUtcTimestampTest(DS, Param.TABLE_NAME.get(), Param.FIELD_DATE.get()),
            new DstUtcTimestampTest(DS, Param.TABLE_NAME.get(), Param.FIELD_TIMESTAMP.get()),
            new DstUtcTimestampTest(DS, Param.TABLE_NAME.get(), Param.FIELD_ZONED.get()),
            new DstUtcTimestampTest(DS, Param.TABLE_NAME.get(), Param.FIELD_LOCAL.get()),
            new NormalUtcTimestampTest(DS, Param.TABLE_NAME.get(), Param.FIELD_DATE.get()),
            new NormalUtcTimestampTest(DS, Param.TABLE_NAME.get(), Param.FIELD_TIMESTAMP.get()),
            new NormalUtcTimestampTest(DS, Param.TABLE_NAME.get(), Param.FIELD_ZONED.get()),
            new NormalUtcTimestampTest(DS, Param.TABLE_NAME.get(), Param.FIELD_LOCAL.get()),
            new PureUtcTimestampTest(DS, Param.TABLE_NAME.get(), Param.FIELD_DATE.get()),
            new PureUtcTimestampTest(DS, Param.TABLE_NAME.get(), Param.FIELD_TIMESTAMP.get()),
            new PureUtcTimestampTest(DS, Param.TABLE_NAME.get(), Param.FIELD_ZONED.get()),
            new PureUtcTimestampTest(DS, Param.TABLE_NAME.get(), Param.FIELD_LOCAL.get())
    };

    public static void main(String[] args) {
        System.out.println("====================================================================");
        System.out.println("START tests: " + Param.DRIVER_CLASS.get() + " v" + DRIVER.getMajorVersion() + "." + DRIVER.getMinorVersion());
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
