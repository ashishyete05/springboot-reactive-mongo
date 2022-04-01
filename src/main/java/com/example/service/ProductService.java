package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;

import com.example.dto.ProductDto;
import com.example.repo.ProductRepository;
import com.example.util.AppUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepo;

	public Flux<ProductDto> getProducts() {
		return productRepo.findAll().map(AppUtils::entityToDto);
	}

	public Mono<ProductDto> getProduct(String id) {
		return productRepo.findById(id).map(AppUtils::entityToDto);
	}

	public Flux<ProductDto> getProductInRange(double min, double max) {
		return productRepo.findByPriceBetween(Range.closed(min, max));
	}

	public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono) {
		return productDtoMono.map(AppUtils::dtoToEntity).flatMap(productRepo::insert).map(AppUtils::entityToDto);
	}

	public Mono<Void> deleteProduct(String id) {
		return productRepo.deleteById(id);
	}

}
