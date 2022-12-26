package com.example.victoralvesapi.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.victoralvesapi.model.Lancamento;
import com.example.victoralvesapi.repository.filter.LancamentoFilter;
import com.example.victoralvesapi.repository.projection.ResumoLancamento;

public interface LancamentoRepositoryQuerry {
	
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageAble);
	Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
}
