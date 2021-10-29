package br.com.zup.SistemaEcommerce.lead;

import br.com.zup.SistemaEcommerce.lead.DTO.LeadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/leads")
public class LeadController {


    @Autowired
    private LeadService servico;

    @GetMapping
    public List<LeadDto> mostrarLeads() {
        return servico.mostrarLead();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adcionarLead (@RequestBody LeadDto leadDto) {
        servico.adicionarLead(leadDto);
    }

}
