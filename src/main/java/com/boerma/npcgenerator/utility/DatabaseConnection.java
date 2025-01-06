package com.boerma.npcgenerator.utility;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DatabaseConnection implements CommandLineRunner {

    private final DataSource dataSource;

    public DatabaseConnection(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Connection to " + connection.getMetaData().getURL() + " established.");
        } catch (SQLException e) {
            System.out.println("Could not connect to database.");
            e.printStackTrace();
        }
    }
}
