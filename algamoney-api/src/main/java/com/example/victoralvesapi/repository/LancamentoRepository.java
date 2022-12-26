package com.example.victoralvesapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.victoralvesapi.model.Lancamento;
import com.example.victoralvesapi.repository.filter.LancamentoFilter;
import com.example.victoralvesapi.repository.lancamento.LancamentoRepositoryQuerry;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuerry{

	Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageAble);
	

}
