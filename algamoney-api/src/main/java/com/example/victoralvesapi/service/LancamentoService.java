package com.example.victoralvesapi.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.victoralvesapi.model.Lancamento;
import com.example.victoralvesapi.model.Pessoa;
import com.example.victoralvesapi.repository.LancamentoRepository;
import com.example.victoralvesapi.repository.PessoaRepository;
import com.example.victoralvesapi.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;

	public Lancamento salvar(@Valid Lancamento lancamento) {
		Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo()).get();
		if(pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		return lancamentoRepository.save(lancamento);
	}

}
