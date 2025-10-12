package com.bits.ride.hailing.bo;

import com.bits.ride.hailing.dto.RiderResponseDTO;
import java.util.Arrays;
import java.util.List;

public class RiderBO {
    public static List<RiderResponseDTO> getAllRiders() {
        RiderResponseDTO r1 = new RiderResponseDTO();
        r1.setId(1L); r1.setName("Alice"); r1.setEmail("alice@example.com"); r1.setPhone("1234567890");
        RiderResponseDTO r2 = new RiderResponseDTO();
        r2.setId(2L); r2.setName("Bob"); r2.setEmail("bob@example.com"); r2.setPhone("2345678901");
        RiderResponseDTO r3 = new RiderResponseDTO();
        r3.setId(3L); r3.setName("Charlie"); r3.setEmail("charlie@example.com"); r3.setPhone("3456789012");
        RiderResponseDTO r4 = new RiderResponseDTO();
        r4.setId(4L); r4.setName("David"); r4.setEmail("david@example.com"); r4.setPhone("4567890123");
        RiderResponseDTO r5 = new RiderResponseDTO();
        r5.setId(5L); r5.setName("Eve"); r5.setEmail("eve@example.com"); r5.setPhone("5678901234");
        return Arrays.asList(r1, r2, r3, r4, r5);
    }
}

