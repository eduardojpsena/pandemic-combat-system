package br.com.ejps.pcas.service;

import br.com.ejps.pcas.exception.ApiRequestException;
import br.com.ejps.pcas.model.Hospital;
import br.com.ejps.pcas.model.Recurso;
import br.com.ejps.pcas.model.dto.HospitalDTO;
import br.com.ejps.pcas.model.dto.PercentualOcupacaoDTO;
import br.com.ejps.pcas.repository.HospitalRepository;
import br.com.ejps.pcas.repository.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospitalService {

    private HospitalRepository repository;
    private RecursoRepository recursoRepository;

    public HospitalService(HospitalRepository repository, RecursoRepository recursoRepository) {
        this.repository = repository;
        this.recursoRepository = recursoRepository;
    }

    public List<Hospital> listar() {
        return repository.findAll();
    }

    public Hospital buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Não foi possível encontrar o hospital com ID: " + id));
    }

    @Transactional
    public Hospital salvar(HospitalDTO dto) {
        Hospital hospital = new Hospital(dto.getNome(), dto.getCnpj(), dto.getEndereco(),
                dto.getLatitude(), dto.getLongitude(), dto.getOcupacao(), LocalDateTime.now());

        List<Recurso> recursos = dto.getRecursos().stream()
                .map(recurso -> new Recurso(recurso.getNome(),
                                            recurso.getTipoRecurso(),
                                            recurso.getQuantidade(),
                                            hospital))
                .collect(Collectors.toList());

        hospital.setRecursos(recursos);

        repository.save(hospital);

        recursoRepository.saveAll(recursos);

        return hospital;
    }

    @Transactional
    public Hospital atualizarOcupacao(Long id, PercentualOcupacaoDTO percentualOcupacaoDTO) {
        Hospital hospital = repository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Não foi possível encontrar o hospital com ID:" + id));

        hospital.setOcupacao(percentualOcupacaoDTO.getOcupacao());
        hospital.setDataAttOcupacao(LocalDateTime.now());

        return hospital;
    }
}
