package com.example.victoralvesapi.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.victoralvesapi.event.RecursoCriadoEvent;
import com.example.victoralvesapi.model.Pessoa;
import com.example.victoralvesapi.repository.PessoaRepository;
import com.example.victoralvesapi.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	 @Autowired
     private PessoaRepository pessoaRepository;
	 
	 @Autowired
	 private ApplicationEventPublisher publisher;
	 
	 @Autowired
	 private PessoaService pessoaService;
	 
     
     @PostMapping
     @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oaut2.hasScope('write')" )
     public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
         Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		
         publisher.publishEvent(new RecursoCriadoEvent(this, response,pessoaSalva.getCodigo() ));
         return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
         
	      
     }
     
     @GetMapping("/{codigo}")
     @PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oaut2.hasScope('write')" )
 	public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long codigo) {
 		Pessoa pessoa = pessoaRepository.findById(codigo).get();
 		return pessoa != null ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
     }
     
     @DeleteMapping("/{codigo}")
     @PreAuthorize("hasAuthority('ROLE_REMOVER_PESSOA') and #oaut2.hasScope('write')" )
 	@ResponseStatus(HttpStatus.NO_CONTENT)
 	public void Remover(@PathVariable Long codigo) {
 		pessoaRepository.deleteById(codigo);
     }
     
     @PutMapping("/{codigo}")
     @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oaut2.hasScope('write')" )
     public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa ){
    	Pessoa pessoaSalva = pessoaService.atualizar(codigo, pessoa);
    	return ResponseEntity.ok(pessoaSalva);
    	
     }
     @PutMapping("/{codigo}/ativo")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
    	 pessoaService.atualizarPropriedadeAtivo(codigo, ativo);
    	 
     }
 		
 }
 