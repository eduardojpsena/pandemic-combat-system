package br.com.ejps.pcas.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class IntercambioDTO {

    private Long hospitalId;
    private List<Long> recursosId;
}
