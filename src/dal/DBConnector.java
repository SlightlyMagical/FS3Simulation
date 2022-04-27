package dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class DBConnector {
    private static final String PROP_FILE = "config.properties";
    private SQLServerDataSource ds;

    /**
     * Constructor for the database connector.
     */
    public DBConnector() throws IOException
    {
        Properties settings = new Properties();
        settings.load(new FileInputStream(PROP_FILE));

        ds = new SQLServerDataSource();
        ds.setServerName(settings.getProperty("DatabaseServer"));
        ds.setDatabaseName(settings.getProperty("Database"));
        ds.setUser(settings.getProperty("Username"));
        ds.setPassword(settings.getProperty("Password"));
    }

    /**
     * Connects the application to our SQL database.
     */
    public Connection getConnection() throws SQLServerException {
        return ds.getConnection();
    }
}
