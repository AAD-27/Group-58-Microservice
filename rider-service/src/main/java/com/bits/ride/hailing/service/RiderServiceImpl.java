package com.bits.ride.hailing.service;

import com.bits.ride.hailing.dto.RiderResponseDTO;
import com.bits.ride.hailing.entity.User;
import com.bits.ride.hailing.entity.RiderEntity;
import com.bits.ride.hailing.repository.RiderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service implementation that provides rider-related operations.
 *
 * <p>This class reads rider data from the {@link RiderRepository} and maps entities to
 * DTOs or simple {@link User} transfer objects for external use.
 */
@Service
public class RiderServiceImpl implements RiderService {

    private static final Logger logger = LoggerFactory.getLogger(RiderServiceImpl.class);
    private static final String CLASS_NAME = RiderServiceImpl.class.getSimpleName();

    private final RiderRepository riderRepository;

    /**
     * Constructor-based injection is preferred for immutability and testability.
     *
     * @param riderRepository repository for accessing rider entities
     */
    public RiderServiceImpl(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
        logger.debug("{}#<init> constructed with RiderRepository={}", CLASS_NAME, riderRepository);
    }

    /**
     * Retrieve a user by id.
     *
     * @param id the id of the user to retrieve; may be null
     * @return a populated {@link User} when found, otherwise null
     */
    @Override
    public User getUser(Long id) {
        final String method = "getUser";
        logger.info("Start of {}: {}() - id={}", CLASS_NAME, method, id);
        if (id == null) {
            logger.info("End of {}: {}() - id={} - resultFound={}", CLASS_NAME, method, id, false);
            return null;
        }
        try {
            logger.debug("{}#{} - Querying RiderRepository.findById for id={}", CLASS_NAME, method, id);
            Optional<RiderEntity> entityOpt = riderRepository.findById(id);
            if (entityOpt.isPresent()) {
                RiderEntity e = entityOpt.get();
                User user = new User();
                user.setId(e.getId());
                user.setName(e.getName());
                user.setEmail(e.getEmail());
                user.setPhone(e.getPhone());
                user.setCreatedAt(e.getCreatedAt());
                logger.debug("{}#{} - Mapped RiderEntity id={} to User", CLASS_NAME, method, id);
                logger.info("End of {}: {}() - id={} - resultFound={}", CLASS_NAME, method, id, true);
                return user;
            }
            logger.debug("{}#{} - No user found with id={}", CLASS_NAME, method, id);
            logger.info("End of {}: {}() - id={} - resultFound={}", CLASS_NAME, method, id, false);
            return null;
        } catch (Exception ex) {
            String msg = CLASS_NAME + "#" + method + ": Failed to retrieve user with id=" + id;
            throw new IllegalStateException(msg, ex);
        }
    }

    /**
     * Retrieve all riders as DTOs.
     *
     * @return list of {@link RiderResponseDTO}; may be empty but never null
     */
    @Override
    public List<RiderResponseDTO> getAllRiders() {
        final String method = "getAllRiders";
        logger.info("Start of {}: {}()", CLASS_NAME, method);
        try {
            List<RiderEntity> entities = riderRepository.findAll();
            List<RiderResponseDTO> list = new ArrayList<>();
            for (RiderEntity e : entities) {
                RiderResponseDTO dto = new RiderResponseDTO();
                dto.setId(e.getId());
                dto.setName(e.getName());
                dto.setEmail(e.getEmail());
                dto.setPhone(e.getPhone());
                dto.setCreatedAt(e.getCreatedAt());
                list.add(dto);
            }
            logger.debug("{}#{} - mapped {} entities to DTOs", CLASS_NAME, method, list.size());
            logger.info("End of {}: {}() - count={}", CLASS_NAME, method, list.size());
            return list;
        } catch (Exception ex) {
            String msg = CLASS_NAME + "#" + method + ": Failed to retrieve all riders";
            // Log with class and method context and full exception
            logger.error("{} - exception retrieving all riders", msg, ex);
            throw new IllegalStateException(msg, ex);
        }
    }
}
