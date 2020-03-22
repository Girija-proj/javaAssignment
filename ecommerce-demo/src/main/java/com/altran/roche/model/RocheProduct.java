package com.altran.roche.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "ROCHE_PRODUCT")
@Getter @NoArgsConstructor
public class RocheProduct { 

/*	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer productId; */
	
	@Id
	@Column(name = "PRODUCT_SKU")
	private UUID sku ;

	@Column(name = "PRODUCT_NAME")
	String name;

	@Column(name = "PRODUCT_PRICE")
	private Double price;
	
	@Column(name = "PRODUCT_QTY")
	private Integer quantity;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "CREATED_AT")
	private LocalDate dateCreated;

	@Setter
	@Column(name = "DEL_YN")
	private String isDelYN;
	

	public RocheProduct(UUID sku,String name, Double price, Integer quantity,LocalDate localDate) {
	   super();
		this.sku = sku;
		this.isDelYN = "N";
		this.name = name;
		this.price = price;		
		this.quantity = quantity;
		this.dateCreated = localDate;
		
	}
	
	
	public RocheProduct(String name, Double price, Integer quantity) {
		super();
		this.sku = UUID.randomUUID();
		this.isDelYN = "N";
		this.name = name;
		this.price = price;		
		this.quantity = quantity;
		dateCreated = LocalDate.now();
		//rocheProduct.setDateCreated(LocalDate.now());
	}

 

}
