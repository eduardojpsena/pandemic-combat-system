package br.com.ejps.pcas.repository;

import br.com.ejps.pcas.model.Hospital;
import br.com.ejps.pcas.model.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecursoRepository extends JpaRepository<Recurso, Long> {

    Optional<Recurso> findByIdAndHospital(Long id, Hospital hospital);
}
