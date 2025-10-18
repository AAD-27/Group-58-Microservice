package com.bits.ride.hailing.service;

import com.bits.ride.hailing.dto.RiderResponseDTO;
import com.bits.ride.hailing.entity.User;
import com.bits.ride.hailing.entity.RiderEntity;
import com.bits.ride.hailing.repository.RiderRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class RiderServiceImpl implements RiderService {

    private static final Logger logger = Logger.getLogger(RiderServiceImpl.class.getName());

    private RiderRepository riderRepository; // optional until DB configured

    @Autowired(required = false)
    public RiderServiceImpl(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    @Override
    public User getUser(Long id) {
        if (id == null) {
            return null;
        }

        List<RiderResponseDTO> riders = loadAllRiders();
        if (riders == null || riders.isEmpty()) {
            return null;
        }

        for (RiderResponseDTO rider : riders) {
            if (rider != null && rider.getId() != null && rider.getId().equals(id)) {
                User user = new User();
                user.setId(rider.getId());
                user.setName(rider.getName());
                user.setEmail(rider.getEmail());
                user.setPhone(rider.getPhone());
                user.setCreatedAt(rider.getCreatedAt());
                return user;
            }
        }

        return null;
    }

    @Override
    public List<RiderResponseDTO> getAllRiders() {
        return loadAllRiders();
    }

    // helper: load from DB using JPA (not used yet; kept for future switch)
    private List<RiderResponseDTO> loadAllRidersFromDb() {
        List<RiderResponseDTO> list = new ArrayList<>();
        try {
            if (riderRepository == null) {
                return list;
            }
            List<RiderEntity> entities = riderRepository.findAll();
            for (RiderEntity e : entities) {
                RiderResponseDTO dto = new RiderResponseDTO();
                dto.setId(e.getId());
                dto.setName(e.getName());
                dto.setEmail(e.getEmail());
                dto.setPhone(e.getPhone());
                dto.setCreatedAt(e.getCreatedAt());
                list.add(dto);
            }
        } catch (Exception ex) {
            logger.fine("DB load skipped or failed: " + ex.getMessage());
        }
        return list;
    }

    // --- CSV loading logic moved from RiderBO ---
    private List<RiderResponseDTO> loadAllRiders() {
        List<RiderResponseDTO> riders = new ArrayList<>();
        String filePath = "src/main/resources/riders.csv";
        String resourcePath = "riders.csv";

        logger.info("Attempting to read riders.csv file from classpath or path: " + resourcePath + " / " + filePath);

        try (BufferedReader br = createReader(resourcePath, filePath)) {
            if (br == null) {
                logger.severe("Could not open riders.csv from classpath or filesystem: " + resourcePath + " / " + filePath);
                return riders;
            }
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] values = line.split(",");
                RiderResponseDTO rider = new RiderResponseDTO();
                try {
                    rider.setId(Long.parseLong(values[0]));
                } catch (Exception e) {
                    logger.warning("Failed to parse id for line: " + line + " - " + e.getMessage());
                    continue;
                }
                if (values.length > 1) rider.setName(values[1]);
                if (values.length > 2) rider.setEmail(values[2]);
                if (values.length > 3) rider.setPhone(values[3]);
                if (values.length > 4) {
                    try {
                        rider.setCreatedAt(Timestamp.valueOf(values[4]));
                    } catch (Exception e) {
                        logger.fine("Failed to parse created_at for line: " + line + " - " + e.getMessage());
                    }
                }

                riders.add(rider);
            }
        } catch (IOException e) {
            logger.severe("Error reading riders.csv file: " + e.getMessage());
        }

        logger.info("Total riders loaded: " + riders.size());
        return riders;
    }

    private BufferedReader createReader(String resourcePath, String filePath) throws IOException {
        InputStream is = RiderServiceImpl.class.getClassLoader().getResourceAsStream(resourcePath);
        if (is != null) {
            return new BufferedReader(new InputStreamReader(is));
        }
        // Fallback to file on disk (useful in development)
        try {
            return new BufferedReader(new FileReader(filePath));
        } catch (IOException e) {
            logger.warning("Failed to open file on disk: " + filePath + " - " + e.getMessage());
            return null;
        }
    }

}

