package br.com.ejps.pcas.repository;

import br.com.ejps.pcas.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
