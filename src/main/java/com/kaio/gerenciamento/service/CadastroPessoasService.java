package com.kaio.gerenciamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaio.gerenciamento.model.Pessoa;
import com.kaio.gerenciamento.repository.PessoasRepository;

@Service
public class CadastroPessoasService {

    @Autowired
    private PessoasRepository pessoas;

    
    public void salvar(Pessoa pessoa) {
        pessoas.guardar(pessoa);
    }

    
    public void excluir(Pessoa pessoa) {
        pessoas.remover(pessoa);
    }
    
    public List<Pessoa> listarTodas() {
        return pessoas.listarTodas();
	}
    
    public Pessoa buscarPorId(Long id) {
        return pessoas.porId(id);
    }
    
}
