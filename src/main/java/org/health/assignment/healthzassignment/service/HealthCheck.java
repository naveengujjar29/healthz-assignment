package org.health.assignment.healthzassignment.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HealthCheck implements IHealthCheck {

    private static final Logger LOGGER = LoggerFactory.getLogger(HealthCheck.class);
    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/mysql";
    private static final String MYSQL_USER = "root";
    private static final String MYSQL_PASSWORD = "";

    @Override
    public boolean checkDBConnectionStatus() {
        boolean isConnectionSuccessful = false;
        try (Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD)) {
            if (connection.isValid(2)) {
                LOGGER.info("Connection is successful.");
                isConnectionSuccessful = true;
            }
        } catch (SQLException e) {
            LOGGER.info("Failed to establish the connection.");
            isConnectionSuccessful = false;
        }
        return isConnectionSuccessful;
    }
}


