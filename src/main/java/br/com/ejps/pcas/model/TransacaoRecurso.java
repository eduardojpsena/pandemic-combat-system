package br.com.ejps.pcas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "transacao_recurso")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class TransacaoRecurso implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Recurso recurso;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnore
    private Intercambio intercambio;

    public TransacaoRecurso(Recurso recurso, Intercambio intercambio) {
        this.recurso = recurso;
        this.intercambio = intercambio;
    }
}
