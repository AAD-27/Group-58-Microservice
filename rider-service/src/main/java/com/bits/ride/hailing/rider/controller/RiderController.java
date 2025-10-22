package com.bits.ride.hailing.rider.controller;

import com.bits.ride.hailing.dto.RiderResponseDTO;
import com.bits.ride.hailing.entity.User;
import com.bits.ride.hailing.service.RiderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST controller that exposes rider-related endpoints.
 *
 * <p>Endpoints provided:
 * <ul>
 *     <li>GET /rider-service/v1/riders - returns a list of riders</li>
 *     <li>GET /rider-service/v1/riders/{id} - returns a specific user by id</li>
 * </ul>
 *
 * This controller delegates business logic to a {@link RiderService}.
 */
@RestController
@RequestMapping("/rider-service")
public class RiderController {

    private static final Logger logger = LoggerFactory.getLogger(RiderController.class);
    private static final String CLASS_NAME = RiderController.class.getSimpleName();

    private final RiderService riderService;

    /**
     * Create a new {@code RiderController} with the given service.
     *
     * @param riderService the service used to handle rider operations; must not be null
     */
    public RiderController(RiderService riderService) {
        this.riderService = riderService;
        logger.info("{}#<init> - initialized", CLASS_NAME);
        logger.debug("{}#<init> - constructed with RiderService={}", CLASS_NAME, riderService);
    }

    /**
     * Retrieve all riders.
     *
     * <p>Returns HTTP 200 with the list of {@link RiderResponseDTO} when successful. The
     * list may be empty if there are no riders.
     *
     * @return ResponseEntity containing the list of riders and HTTP status 200
     */
    @GetMapping("/v1/riders")
    public ResponseEntity<List<RiderResponseDTO>> getAllRiders() {
        final String method = "getAllRiders";
        logger.info("Start of {}: {}()", CLASS_NAME, method);
        logger.debug("{}#{} - Handling request: GET /v1/riders - delegating to RiderService.getAllRiders", CLASS_NAME, method);
        List<RiderResponseDTO> all = riderService.getAllRiders();
        logger.debug("{}#{} - RiderService.getAllRiders returned {} entries", CLASS_NAME, method, all == null ? 0 : all.size());
        logger.info("End of {}: {}() - count={}", CLASS_NAME, method, all == null ? 0 : all.size());
        return ResponseEntity.ok(all);
    }

    /**
     * Retrieve a user by id.
     *
     * <p>Returns HTTP 200 with the {@link User} when the user exists, or HTTP 404 when
     * no user is found for the provided id.
     *
     * @param id the id of the user to retrieve (from the path variable)
     * @return ResponseEntity with the found User and HTTP 200, or HTTP 404 when not found
     */
    @GetMapping("/v1/riders/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        final String method = "getUser";
        logger.info("Start of {}: {}() - id={}", CLASS_NAME, method, id);
        logger.debug("{}#{} - Handling request: GET /v1/riders/{} - calling RiderService.getUser", CLASS_NAME, method, id);
        User user = riderService.getUser(id);
        if (user == null) {
            logger.debug("{}#{} - RiderService.getUser returned null for id={}", CLASS_NAME, method, id);
            logger.info("End of {}: {}() - id={} - resultFound={}", CLASS_NAME, method, id, false);
            return ResponseEntity.notFound().build();
        }
        logger.debug("{}#{} - RiderService.getUser returned user for id={}", CLASS_NAME, method, id);
        logger.info("End of {}: {}() - id={} - resultFound={}", CLASS_NAME, method, id, true);
        return ResponseEntity.ok(user);
    }
}
