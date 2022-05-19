package br.com.ejps.pcas.controller;

import br.com.ejps.pcas.model.dto.RelatorioDTO;
import br.com.ejps.pcas.service.RelatorioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    private RelatorioService service;

    public RelatorioController(RelatorioService service) {
        this.service = service;
    }

    @GetMapping
    public RelatorioDTO gerar () {
        return service.gerarRelatorio();
    }
}
