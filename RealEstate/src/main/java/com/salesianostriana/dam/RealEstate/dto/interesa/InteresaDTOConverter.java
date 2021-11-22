package com.salesianostriana.dam.RealEstate.dto.interesa;

import com.salesianostriana.dam.RealEstate.model.Interesa;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class InteresaDTOConverter {

    public Interesa create(GetInteresaDTO i) {
        return Interesa.builder()
                .createdDate(LocalDateTime.now())
                .mensaje(i.getMensaje())
                .build();
    }
}
