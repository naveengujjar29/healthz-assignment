package org.health.assignment.healthzassignment.controller;


import com.fasterxml.jackson.databind.JsonNode;

import org.health.assignment.healthzassignment.service.IHealthCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller class will be used to check the health of application with DB.
 */
@RestController
@RequestMapping("/healthz")
public class HealthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HealthController.class);


    @Autowired
    private IHealthCheck healthCheck;

    @GetMapping
    public ResponseEntity checkDatabaseHealth(@RequestBody(required = false) JsonNode body) {
        if (body != null) {
            LOGGER.error("Body is not supported in GET API.");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl("no-cache");
        boolean connectionStatus = healthCheck.checkDBConnectionStatus();
        if (connectionStatus) {
            LOGGER.info("Failed to get the connection.");
            return new ResponseEntity("Database is up and running", headers, HttpStatus.OK);
        }
        return new ResponseEntity("Database is not up and running", headers, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
