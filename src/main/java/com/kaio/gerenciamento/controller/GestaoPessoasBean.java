package com.kaio.gerenciamento.controller;
import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.kaio.gerenciamento.model.Pessoa;
import com.kaio.gerenciamento.repository.PessoasRepository;
import com.kaio.gerenciamento.service.CadastroPessoasService;
import com.kaio.gerenciamento.util.FacesMessages;

@RestController
@RequestMapping("/api/pessoas") // Mapeia a raiz da URL para /api/pessoas
@Scope(WebApplicationContext.SCOPE_SESSION)
public class GestaoPessoasBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Autowired
    private PessoasRepository pessoasRepository;
    
    private Pessoa pessoa;
    
    @Autowired
    private CadastroPessoasService cadastroPessoasService;
    
    @Autowired
    private FacesMessages messages;

    private List<Pessoa> listaPessoasRepository;
    
    private String termoPesquisa;
    
    @PostMapping("/nova") 
    public void prepararNovaPessoa(){
        pessoa = new Pessoa();
    }
    
    @GetMapping("/edicao/{id}") 
    public void prepararEdicao(@PathVariable Long id){
         pessoa = cadastroPessoasService.buscarPorId(id);
    }
    
    @PostMapping("/salvar") 
    public void salvar(){
        cadastroPessoasService.salvar(pessoa);
        
        atualizarRegistros();
        
        messages.info("Pessoa salva com sucesso!");
    }
    
    @DeleteMapping("/excluir/{id}") 
    public void excluir(@PathVariable Long id){
        cadastroPessoasService.excluir(pessoa);
        
        pessoa = null;
        
        atualizarRegistros();
        
        messages.info("Pessoa excluída com sucesso!");
    }
    
    @PostMapping("/pesquisar") 
    public void pesquisar(){
        listaPessoasRepository = pessoasRepository.pesquisar(termoPesquisa);
        
        if(listaPessoasRepository.isEmpty()){
            messages.info("Sua consulta não retornou registros.");
        }
    }
    
    @GetMapping("/todas")
    public void todasPessoasRepository() {
        listaPessoasRepository = pessoasRepository.listarTodas();
        System.out.println("Tamanho da lista de PessoasRepository: " + (listaPessoasRepository != null ? listaPessoasRepository.size() : 0));
    }
    
    private void atualizarRegistros(){
        if(jaHouvePesquisa()) {
            pesquisar();
        } else {
            todasPessoasRepository();
        }
    }
    
    private boolean jaHouvePesquisa(){
        return termoPesquisa != null && !"".equals(termoPesquisa);
    }
}
