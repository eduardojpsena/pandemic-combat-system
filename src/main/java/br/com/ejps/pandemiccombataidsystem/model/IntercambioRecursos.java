package br.com.ejps.pandemiccombataidsystem.model;

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
public class IntercambioRecursos implements Serializable {
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(name = "recursos_enviados_intercambio",
            joinColumns = {@JoinColumn(name = "intercambio_id")},
            inverseJoinColumns = {@JoinColumn(name = "recurso_id")})
    private List<Recurso> recursosEnviados;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(name = "recursos_recebidos_intercambio",
            joinColumns = {@JoinColumn(name = "intercambio_id")},
            inverseJoinColumns = {@JoinColumn(name = "recurso_id")})
    private List<Recurso> recursosRecebidos;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataIntercambio;
}
