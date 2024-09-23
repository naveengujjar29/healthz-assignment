package org.health.assignment.healthzassignment.controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller class will be used to check the health of application with DB.
 */
@RestController
@RequestMapping("/healthz")
public class HealthController {

    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/mysql";
    private static final String MYSQL_USER = "root";
    private static final String MYSQL_PASSWORD = "";


    @GetMapping
    public ResponseEntity checkDatabaseHealth() {
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl("no-cache");
        // It will try to establish a connection with MySQL DB, if connection is okay then return OK(200 status else
        // 503 SERVICE_UNAVAILABLE).
        try(Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD)) {
            if (connection.isValid(2)) {
                return new ResponseEntity("Database connection is up and running.", headers, HttpStatus.OK);
            }
            return new ResponseEntity("Database connection is not up and running.", headers,
                    HttpStatus.SERVICE_UNAVAILABLE);
        } catch (SQLException e) {
            return new ResponseEntity("Database connection is not up and running.", headers,
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }


}
