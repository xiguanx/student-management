package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.NameRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class NameAggregationController {

    private static final Logger logger = LoggerFactory.getLogger(NameAggregationController.class);

    // Krystal's endpoint (skipping Rachel who has no IP)
    private static final String NEXT_URL = "http://18.225.218.150:8088/name/aggregation";

    private final RestTemplate restTemplate;

    public NameAggregationController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/name/aggregation")
    public ResponseEntity<NameRequest> aggregate(@RequestBody NameRequest request) {
        logger.info("Received request with names: {}", request.getName());

        // Add own name to the list
        request.getName().add("Liz");
        logger.info("Added Liz, forwarding to next: {}", NEXT_URL);

        // Forward to next student (Krystal)
        try {
            ResponseEntity<NameRequest> response = restTemplate.postForEntity(
                NEXT_URL, request, NameRequest.class
            );
            logger.info("Response from downstream: {}", response.getBody());
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            logger.error("Failed to forward to downstream: {}", e.getMessage());
            // If downstream is unavailable, return current state
            return ResponseEntity.ok(request);
        }
    }

    // Health check endpoint
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Student Management App is running!");
    }
}
