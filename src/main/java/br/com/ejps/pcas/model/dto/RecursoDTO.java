package br.com.ejps.pcas.model.dto;

import br.com.ejps.pcas.model.enumeration.TipoRecurso;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class RecursoDTO {

    @Getter @Setter private String nome;
    private Integer tipoRecurso;

    public RecursoDTO(String nome, TipoRecurso tipoRecurso) {
        this.nome = nome;
        setTipoRecurso(tipoRecurso);
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
