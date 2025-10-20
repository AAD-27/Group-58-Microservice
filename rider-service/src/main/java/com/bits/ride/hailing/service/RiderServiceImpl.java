package com.bits.ride.hailing.service;

import com.bits.ride.hailing.dto.RiderResponseDTO;
import com.bits.ride.hailing.entity.User;
import com.bits.ride.hailing.entity.RiderEntity;
import com.bits.ride.hailing.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class RiderServiceImpl implements RiderService {

    private static final Logger logger = Logger.getLogger(RiderServiceImpl.class.getName());

    @Autowired
    RiderRepository riderRepository;


    @Override
    public User getUser(Long id) {
        if (id == null) return null;
        try {
            Optional<RiderEntity> entityOpt = riderRepository.findById(id);
            if (entityOpt.isPresent()) {
                RiderEntity e = entityOpt.get();
                User user = new User();
                user.setId(e.getId());
                user.setName(e.getName());
                user.setEmail(e.getEmail());
                user.setPhone(e.getPhone());
                user.setCreatedAt(e.getCreatedAt());
                return user;
            }
            return null;
        } catch (Exception ex) {
            logger.severe("DB getUser failed: " + ex.getMessage());
            throw ex;
        }
    }

    @Override
    public List<RiderResponseDTO> getAllRiders() {
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
            return list;
        } catch (Exception ex) {
            logger.severe("DB getAllRiders failed: " + ex.getMessage());
            throw ex;
        }
    }
}
