package br.com.ejps.pcas.model.dto;

import br.com.ejps.pcas.model.enumeration.TipoRecurso;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class MediaRecursosDTO {

    private Integer tipoRecurso;
    @Getter @Setter private Double quantidade;

    public MediaRecursosDTO(TipoRecurso tipoRecurso, Double quantidade) {
        setTipoRecurso(tipoRecurso);
        this.quantidade = quantidade;
    }

    public TipoRecurso getTipoRecurso() {
        return TipoRecurso.valueOf(tipoRecurso);
    }

    public void setTipoRecurso(TipoRecurso tipoRecurso) {
        if (tipoRecurso != null) {
            this.tipoRecurso = tipoRecurso.getCodigo();
        }
    }
}
