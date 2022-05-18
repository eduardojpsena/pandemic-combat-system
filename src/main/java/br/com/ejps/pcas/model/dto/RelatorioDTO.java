package br.com.ejps.pcas.model.dto;

import br.com.ejps.pcas.model.Hospital;
import br.com.ejps.pcas.model.Intercambio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class RelatorioDTO {

    private Double porcentagemHospitaisSuperlotados;
    private Double porcentagemHospitaisVagos;
    private Hospital hospitalSuperlotadoMaisTempo;
    private Hospital hospitalVagoMaisTempo;
    private List<MediaRecursosDTO> mediaRecursos;
    private List<Intercambio> historicoIntercambios;


}
