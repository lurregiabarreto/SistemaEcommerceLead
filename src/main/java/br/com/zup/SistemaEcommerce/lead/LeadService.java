package br.com.zup.SistemaEcommerce.lead;


import br.com.zup.SistemaEcommerce.exception.LeadEProdutoJaCadastroException;
import br.com.zup.SistemaEcommerce.exception.LeadNaoEncontradoException;
import br.com.zup.SistemaEcommerce.lead.DTO.LeadDto;
import br.com.zup.SistemaEcommerce.lead.DTO.ProdutoDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeadService {
    private List<LeadDto> mailing = new ArrayList<>();

    public void salvarLead(LeadDto leadDto){
        try{
            verificarLeadEProduto(leadDto);
            LeadDto leadDaLista = buscarLead(leadDto.getEmail());
            atualizarListaDeProdutos(leadDto.getProdutos(), leadDaLista);

        }catch (LeadNaoEncontradoException exception){
            mailing.add(leadDto);
        }

    }

    public void verificarLeadEProduto(LeadDto objetoChegandoAgora){
        LeadDto objetoDaLista = buscarLead(objetoChegandoAgora.getEmail());

        for (ProdutoDto produtoDto : objetoChegandoAgora.getProdutos()){
            if (produtoEstaPresente(objetoDaLista.getProdutos(), produtoDto.getNome())){
                throw new LeadEProdutoJaCadastroException("Lead e Produto já cadastrado");
            }
        }

    }

    public LeadDto buscarLead(String email){
        for(LeadDto leadDTO : mailing){
            if(leadDTO.getEmail().equalsIgnoreCase(email)){
                return leadDTO;
            }
        }
        throw new LeadNaoEncontradoException("Lead não encontrado");
    }

    public boolean produtoEstaPresente(List<ProdutoDto> listaDeInteresse, String nomeProduto){
        for(ProdutoDto produtoDTO : listaDeInteresse){
            if (produtoDTO.getNome().equalsIgnoreCase(nomeProduto)){
                return true;
            }
        }
        return false;
    }

    public void atualizarListaDeProdutos(List<ProdutoDto> novosProdutos, LeadDto leadParaAtualizar){
        for (ProdutoDto produtoDTO : novosProdutos){
            leadParaAtualizar.getProdutos().add(produtoDTO);
        }
    }

    public List<LeadDto> retornarTodosOsLead(){
        return mailing;
    }

}