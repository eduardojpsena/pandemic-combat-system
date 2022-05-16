package br.com.ejps.pcas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "intercambio")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Intercambio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "hospital_origem_id")
    private Hospital hospitalOrigem;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "hospital_destino_id")
    private Hospital hospitalDestino;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataIntercambio;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "intercambio", cascade = CascadeType.ALL)
    private List<TransacaoRecurso> transacaoRecursos;
}
