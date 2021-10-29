package br.com.zup.SistemaEcommerce.lead;


import br.com.zup.SistemaEcommerce.lead.DTO.LeadDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeadService {
    private List<LeadDto> listaDeLead = new ArrayList<>();


    public List<LeadDto> mostrarLead() {
        return listaDeLead;
    }

    public void adicionarLead(@RequestBody  LeadDto leadDto){
        for (LeadDto referencia : listaDeLead){
            if(referencia.getEmail().equalsIgnoreCase(leadDto.getEmail())){
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }
        }
        listaDeLead.add(leadDto);
    }
}
