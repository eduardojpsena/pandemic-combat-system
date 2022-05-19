package br.com.ejps.pcas.service;

import br.com.ejps.pcas.exception.ApiException;
import br.com.ejps.pcas.exception.ApiRequestException;
import br.com.ejps.pcas.model.Hospital;
import br.com.ejps.pcas.model.Intercambio;
import br.com.ejps.pcas.model.Recurso;
import br.com.ejps.pcas.model.TransacaoRecurso;
import br.com.ejps.pcas.model.dto.HospitalDTO;
import br.com.ejps.pcas.model.dto.IntercambioHospitalDTO;
import br.com.ejps.pcas.repository.HospitalRepository;
import br.com.ejps.pcas.repository.IntercambioRepository;
import br.com.ejps.pcas.repository.RecursoRepository;
import br.com.ejps.pcas.repository.TransacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IntercambioService {

    private IntercambioRepository repository;
    private HospitalService hospitalService;
    private RecursoRepository recursoRepository;
    private TransacaoRepository transacaoRepository;

    public IntercambioService(IntercambioRepository repository, HospitalService hospitalService,
                              RecursoRepository recursoRepository, TransacaoRepository transacaoRepository) {
        this.repository = repository;
        this.hospitalService = hospitalService;
        this.recursoRepository = recursoRepository;
        this.transacaoRepository = transacaoRepository;
    }

    public List<Recurso> buscarRecursos(Hospital hospitalOrigem, List<Long> recursosHospitalOrigemId) {
        return recursosHospitalOrigemId.stream()
                .map(recursoId -> recursoRepository.findByIdAndHospital(recursoId, hospitalOrigem).get())
                .collect(Collectors.toList());
    }

    public Integer calcularValorRecursos (List<Recurso> recursosHospitais) {
        return recursosHospitais.stream()
                .distinct()
                .map(recurso -> recurso.getTipoRecurso().getPontos())
                .reduce(0, (acc, valorRecurso) -> acc + valorRecurso);
    }

    @Transactional
    public List<Intercambio> intercambioRecursos(IntercambioHospitalDTO intercambioHospitalDTO) {

        Hospital hospitalOrigem = hospitalService.buscarPorId(intercambioHospitalDTO.getHospitalOrigem().getHospitalId());
        Hospital hospitalDestino = hospitalService.buscarPorId(intercambioHospitalDTO.getHospitalDestino().getHospitalId());
        //--------------------------------------------------------------------------------------

        List<Recurso> recursosHospitalOrigem = buscarRecursos(hospitalOrigem,
                intercambioHospitalDTO.getHospitalOrigem().getRecursosId());
        List<Recurso> recursosHospitalDestino = buscarRecursos(hospitalDestino,
                intercambioHospitalDTO.getHospitalDestino().getRecursosId());
        //--------------------------------------------------------------------------------------

        int valorRecursosHospitalOrigem = calcularValorRecursos(recursosHospitalOrigem);
        int valorRecursosHospitalDestino = calcularValorRecursos(recursosHospitalDestino);
        //--------------------------------------------------------------------------------------

        if ((hospitalOrigem.getOcupacao() >= 90) &&
                (valorRecursosHospitalOrigem > valorRecursosHospitalDestino)) {
            throw new ApiRequestException("");
        }
        if ((hospitalDestino.getOcupacao() >= 90) &&
                (valorRecursosHospitalDestino > valorRecursosHospitalOrigem)) {
            throw new ApiRequestException("");
        }
        if ((hospitalOrigem.getOcupacao() < 90) &&
                (hospitalDestino.getOcupacao() < 90) &&
                    (valorRecursosHospitalOrigem != valorRecursosHospitalDestino)) {
            throw new ApiRequestException("");
        }
        //--------------------------------------------------------------------------------------

        Intercambio intercambioHospitalOrigem = new Intercambio(hospitalOrigem, hospitalDestino);
        Intercambio intercambioHospitalDestino = new Intercambio(hospitalDestino, hospitalOrigem);
        //--------------------------------------------------------------------------------------

        List<TransacaoRecurso> transacoesHospitalOrigem = recursosHospitalOrigem.stream()
                .map(recurso -> new TransacaoRecurso(recurso, intercambioHospitalOrigem))
                .collect(Collectors.toList());
        List<TransacaoRecurso> transacoesHospitalDestino = recursosHospitalDestino.stream()
                .map(recurso -> new TransacaoRecurso(recurso, intercambioHospitalDestino))
                .collect(Collectors.toList());
        //--------------------------------------------------------------------------------------

        intercambioHospitalOrigem.setTransacaoRecursos(transacoesHospitalOrigem);
        intercambioHospitalDestino.setTransacaoRecursos(transacoesHospitalDestino);
        //--------------------------------------------------------------------------------------

        repository.save(intercambioHospitalOrigem);
        repository.save(intercambioHospitalDestino);
        //--------------------------------------------------------------------------------------

        transacaoRepository.saveAll(transacoesHospitalOrigem);
        transacaoRepository.saveAll(transacoesHospitalDestino);
        //--------------------------------------------------------------------------------------

        recursosHospitalOrigem.stream()
                .forEach(recurso -> recurso.setHospital(hospitalDestino));
        recursosHospitalDestino.stream()
                .forEach(recurso -> recurso.setHospital(hospitalOrigem));
        //--------------------------------------------------------------------------------------

        return List.of(intercambioHospitalOrigem, intercambioHospitalDestino);
    }
}
