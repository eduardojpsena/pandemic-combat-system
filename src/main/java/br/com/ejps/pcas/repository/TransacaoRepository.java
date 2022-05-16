package br.com.ejps.pcas.repository;

import br.com.ejps.pcas.model.TransacaoRecurso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<TransacaoRecurso, Long> {
}
