package com.belval.crudrest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.belval.crudrest.model.Produto;
import com.belval.crudrest.repository.ProdutoRepository;

@RestController
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	/**
	 * Retorna todos os produtos
	 * @return
	 */
	@GetMapping("/produtos")
	public ResponseEntity<Iterable<Produto>> obterProdutos() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(repository.findAll());
	}
	
	@GetMapping("/produtos/{id}")
	public ResponseEntity<Object> buscarPorId(
			@PathVariable(value = "id") Integer id) {
		
		Optional<Produto> produto = repository.findById(id);
		
		if (produto.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(produto.get());
		}
		
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body("Produto não encontrado!");
				
	}
	
	
	//curl POST http://localhost:8080/produtos -H "Content-Type: application/json; Charset=utf-8" -d @produto-pao.json
	
	@PostMapping("/produtos")
	public ResponseEntity<Produto> criarProduto(
			@RequestBody Produto produto) {
		
		//produto.setId(proxId++);
		
		System.out.println("Produto criado ... " + produto.toString());
		repository.save(produto);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(produto);
		
	}
	
	

}
