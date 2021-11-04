package br.com.zup.SistemaEcommerce.lead;


import br.com.zup.SistemaEcommerce.lead.DTO.LeadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/leads")
//Proxy Pattern
public class LeadController {
    @Autowired
    private LeadService leadService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarLead(@RequestBody @Valid LeadDto leadDto) {
        leadService.salvarLead(leadDto);
    }

    @GetMapping
    public List<LeadDto> exibirMailing() {
        return leadService.retornarTodosOsLead();
    }

    @GetMapping("/{email}")
    public LeadDto buscarLeadPeloEmail(@PathVariable String email) {
        return leadService.buscarLead(email);
    }
}