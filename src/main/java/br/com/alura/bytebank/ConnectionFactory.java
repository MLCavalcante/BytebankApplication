package br.com.alura.bytebank;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection recuperarConexao(){
        try {

            return createDataSource().getConnection();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private HikariDataSource createDataSource() {

        Dotenv dotenv = Dotenv.load();
        String mysqlDatabase = dotenv.get("MYSQL_DATABASE");
        String mysqlRootPassword = dotenv.get("MYSQL_ROOT_PASSWORD");

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/"+ mysqlDatabase);
        config.setUsername("root");
        config.setPassword(mysqlRootPassword);
        config.setMaximumPoolSize(10);

        return new HikariDataSource(config);
    }

}