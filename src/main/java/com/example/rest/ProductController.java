package com.example.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ProductDto;
import com.example.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProductController {

	@Autowired
	private ProductService prodService;
	
	@GetMapping("/getAllProducts")
	public Flux<ProductDto> getProducts(){
		return prodService.getProducts();
	}
	
	@GetMapping("/getProduct/{id}")
	public Mono<ProductDto> getProduct(@PathVariable String id){
		return prodService.getProduct(id);
	}
	
	@PostMapping("/saveProduct")
	public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDto){
		return prodService.saveProduct(productDto);
	}
	
	
	@DeleteMapping("/deleteProduct/{id}")
	public Mono<Void> deleteProduct(@PathVariable String id){
		return prodService.deleteProduct(id);
	}
}

