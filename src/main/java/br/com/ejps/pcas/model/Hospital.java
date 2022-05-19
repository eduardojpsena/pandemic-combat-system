package br.com.ejps.pcas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "hospital")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Hospital implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cnpj;
    private String endereco;
    private Double latitude;
    private Double longitude;
    private Double ocupacao;
    private LocalDateTime dataAttOcupacao;

    @OneToMany(mappedBy = "hospital")
    private List<Recurso> recursos;

    public Hospital(String nome, String cnpj, String endereco, Double latitude,
                    Double longitude, Double ocupacao, LocalDateTime dataAttOcupacao) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.latitude = latitude;
        this.longitude = longitude;
        this.ocupacao = ocupacao;
        this.dataAttOcupacao = dataAttOcupacao;
    }
}
