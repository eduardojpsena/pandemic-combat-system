package br.com.ejps.pcas.model.enumeration;

import lombok.Getter;
import lombok.Setter;

public enum TipoRecurso {

    ENFERMEIRO(1, 3),
    MEDICO(2, 3),
    RESPIRADOR(3, 5),
    TOMOGRAFO(4, 12),
    AMBULANCIA(5,  10);

    @Getter @Setter private Integer codigo;

    @Getter @Setter private Integer pontos;

    TipoRecurso(Integer codigo, Integer pontos) {
        this.codigo = codigo;
        this.pontos = pontos;
    }

    public static TipoRecurso valueOf(Integer codigo) {
        for (TipoRecurso tr: values()) {
            if (tr.getCodigo().equals(codigo)) {
                return tr;
            }
        }
        return null;
    }
}
