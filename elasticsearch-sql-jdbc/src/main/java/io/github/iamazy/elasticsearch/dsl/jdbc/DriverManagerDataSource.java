package io.github.iamazy.elasticsearch.dsl.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author iamazy
 * @date 2019/12/21
 **/
public class DriverManagerDataSource extends AbstractDriverBasedDataSource {

    public DriverManagerDataSource() {
    }

    public DriverManagerDataSource(String url) {
        setUrl(url);
    }

    public DriverManagerDataSource(String url, Properties properties) {
        setUrl(url);
        setProperties(properties);
    }


    @Override
    protected Connection getConnectionFromDriver(Properties props) throws SQLException {
        return getConnectionFromDriverManager(getUrl(),props);
    }

    protected Connection getConnectionFromDriverManager(String url,Properties properties) throws SQLException {
        return DriverManager.getConnection(url,properties);
    }
}
