package com.bits.ride.hailing.bo;

import com.bits.ride.hailing.dto.RiderResponseDTO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class RiderBO {
    private static final Logger logger = Logger.getLogger(RiderBO.class.getName());

    public static List<RiderResponseDTO> getAllRiders() {
        List<RiderResponseDTO> riders = new ArrayList<>();
        String filePath = "src/main/resources/riders.csv";

        logger.info("Attempting to read riders.csv file from path: " + filePath);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                String[] values = line.split(",");
                RiderResponseDTO rider = new RiderResponseDTO();
                rider.setId(Long.parseLong(values[0]));
                rider.setName(values[1]);
                rider.setEmail(values[2]);
                rider.setPhone(values[3]);
                riders.add(rider);
                logger.info("Successfully added rider: " + rider);
            }
        } catch (IOException e) {
            logger.severe("Error reading riders.csv file: " + e.getMessage());
            e.printStackTrace();
        }

        logger.info("Total riders loaded: " + riders.size());
        return riders;
    }
}
