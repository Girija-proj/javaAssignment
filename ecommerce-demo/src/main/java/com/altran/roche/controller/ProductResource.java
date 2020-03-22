package com.altran.roche.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.altran.roche.model.Product;
import com.altran.roche.model.ResultCode;
import com.altran.roche.model.RocheProduct;
import com.altran.roche.service.ProductService;
import com.altran.roche.util.RocheConstant;

@RestController
public class ProductResource {	

	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public List<Product> getProducts() {
		List<RocheProduct> rocheProducts = productService.getAllProducts();
		return rocheProducts.stream().filter(p -> p.getIsDelYN().equalsIgnoreCase(RocheConstant.N))
				.map(p -> new Product(p.getSku(), p.getName(), p.getPrice(), p.getQuantity()))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/products/{sku}")
	public Product getProductById(@PathVariable("sku") UUID sku) {
		  RocheProduct rocheProduct = productService.findBySku(sku);
	      return new Product(rocheProduct.getSku(), rocheProduct.getName(), rocheProduct.getPrice(), rocheProduct.getQuantity());
	}
	

	@PostMapping("/products")
	public void createProducts(@RequestParam String name,@RequestParam Double price,@RequestParam Integer quantity) {
		Product product = new Product(null ,name, price, quantity);
		productService.saveProduct(product);
	}

	@PutMapping("/products/{sku}")
	public ResponseEntity<ResultCode> updateProduct(@RequestParam String name,@RequestParam Double price,@RequestParam Integer quantity, @PathVariable String sku) {
		try {
			UUID uuid = UUID.fromString(sku);
			RocheProduct rocheProduct = productService.findBySku(uuid);
			ResultCode code = null;			
			if (null != rocheProduct) {
				
				productService.updateProduct(name,price,quantity,uuid,rocheProduct.getDateCreated());
				code = new ResultCode("SUCCESS");
				return new ResponseEntity<ResultCode>(code, HttpStatus.OK);
			} else {
				code = new ResultCode("FAILURE");
			}
			return new ResponseEntity<ResultCode>(code, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			ResultCode code = new ResultCode("FAILURE");
			return new ResponseEntity<ResultCode>(code , HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/products/{sku}")
	public ResponseEntity<ResultCode> deleteProducts(@PathVariable UUID sku) {
		productService.deleteBySku(sku);
		 ResultCode code = new ResultCode("SUCCESS");
		return new ResponseEntity<ResultCode>(code, HttpStatus.OK);
	}

}