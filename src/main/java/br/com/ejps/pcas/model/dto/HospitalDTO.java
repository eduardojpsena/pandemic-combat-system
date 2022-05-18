package br.com.ejps.pcas.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class HospitalDTO {

    private String nome;
    private String cnpj;
    private String endereco;
    private Double latitude;
    private Double longitude;
    private Double ocupacao;

    private List<RecursoDTO> recursos;

}
