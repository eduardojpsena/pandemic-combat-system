package br.com.ejps.pcas.controller;

import br.com.ejps.pcas.model.Hospital;
import br.com.ejps.pcas.model.dto.HospitalDTO;
import br.com.ejps.pcas.model.dto.PercentualOcupacaoDTO;
import br.com.ejps.pcas.service.HospitalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospitais")
public class HospitalController {

    private HospitalService service;

    public HospitalController(HospitalService service) {
        this.service = service;
    }

    @GetMapping
    public List<Hospital> listar() {
        return service.listar();
    }

    @GetMapping(path = "{id}")
    public Hospital buscarPorId( @PathVariable Long id ) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Hospital salvar(@RequestBody HospitalDTO hospitalDTO ) {
        return service.salvar(hospitalDTO);
    }

    @PatchMapping(path = {"{id}"})
    public Hospital atualizarOcupacao(@PathVariable Long id, @RequestBody PercentualOcupacaoDTO percentualOcupacaoDTO ) {
        return service.atualizarOcupacao(id, percentualOcupacaoDTO);
    }
}
