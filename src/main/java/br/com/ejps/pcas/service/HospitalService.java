package br.com.ejps.pcas.service;

import br.com.ejps.pcas.model.Hospital;
import br.com.ejps.pcas.model.Recurso;
import br.com.ejps.pcas.model.dto.HospitalDTO;
import br.com.ejps.pcas.model.dto.PercentualOcupacaoDTO;
import br.com.ejps.pcas.repository.HospitalRepository;
import br.com.ejps.pcas.repository.RecursoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        return repository.findById(id).get();
    }

    @Transactional
    public Hospital salvar(HospitalDTO dto) {
        Hospital hospital = new Hospital(dto.getNome(), dto.getCnpj(), dto.getEndereco(),
                dto.getLatitude(), dto.getLongitude(), dto.getOcupacao());

        List<Recurso> recursos = dto.getRecursos().stream()
                .map(recurso -> new Recurso(recurso.getNome(), recurso.getTipoRecurso(), recurso.getQuantidade(), hospital))
                .collect(Collectors.toList());

        hospital.setRecursos(recursos);

        repository.save(hospital);

        recursoRepository.saveAll(recursos);

        return hospital;
    }

    @Transactional
    public Hospital atualizarOcupacao(Long id, PercentualOcupacaoDTO percentualOcupacaoDTO) {
        Hospital hospital = repository.findById(id).get();

        hospital.setOcupacao(percentualOcupacaoDTO.getOcupacao());

        return hospital;
    }
}
