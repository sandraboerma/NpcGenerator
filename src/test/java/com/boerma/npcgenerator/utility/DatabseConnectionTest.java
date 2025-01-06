package com.boerma.npcgenerator.utility;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DatabaseConnectionTest {

    @Autowired
    private DatabaseConnection databaseConnection;

    @Test
    void testStartupConnection() {
        databaseConnection.run();
    }

}
