package io.github.iamazy.elasticsearch.dsl.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author iamazy
 * @date 2019/12/16
 **/
public abstract class AbstractDriverBasedDataSource extends AbstractDataSource {

    private String url;

    private Properties properties;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Properties getProperties() {
        return properties;
    }

    /**
     * @param properties {
     *                   driverClassName: "",
     *                   url: "",
     *                   user: "",
     *                   password:""
     *                   // todo 其他restHighLevelClient官方支持的配置，如超时时间
     *                   }
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
        if (properties != null && properties.containsKey("driverClassName")) {
            this.setDriverClassName(properties.getProperty("driverClassName"));
        }
    }

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader classLoader = null;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        } catch (Throwable cause) {
            cause.printStackTrace();
        }
        if (classLoader == null) {
            classLoader = DriverManagerDataSource.class.getClassLoader();
            if (classLoader == null) {
                try {
                    classLoader = ClassLoader.getSystemClassLoader();
                } catch (Throwable cause) {
                    cause.printStackTrace();
                }
            }
        }
        return classLoader;
    }


    public void setDriverClassName(String driverClassName) {
        String driverClass = driverClassName.trim();
        try {
            Class.forName(driverClass, true, getDefaultClassLoader());
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Could not load JDBC driver class [" + driverClass + "]", e);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return getConnectionFromDriver();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return getConnection();
    }

    protected Connection getConnectionFromDriver() throws SQLException {
        if (properties != null) {
            return getConnectionFromDriver(properties);
        }
        return getConnectionFromDriver(new Properties());
    }

    protected abstract Connection getConnectionFromDriver(Properties props) throws SQLException;
}
