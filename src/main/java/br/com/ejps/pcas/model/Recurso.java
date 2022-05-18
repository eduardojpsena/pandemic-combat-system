package br.com.ejps.pcas.model;

import br.com.ejps.pcas.model.enumeration.TipoRecurso;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "recurso")
@Getter @Setter
@NoArgsConstructor
public class Recurso implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Getter @Setter private String nome;
    private Integer tipoRecurso;
    @Getter @Setter private Integer quantidade;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="hospital_id")
    @Getter @Setter private Hospital hospital;

    public Recurso(Long id, String nome, TipoRecurso tipoRecurso, Integer quantidade, Hospital hospital) {
        this.id = id;
        this.nome = nome;
        setTipoRecurso(tipoRecurso);
        this.quantidade = quantidade;
        this.hospital = hospital;
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
