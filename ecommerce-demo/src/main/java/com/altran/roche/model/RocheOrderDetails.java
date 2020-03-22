package com.altran.roche.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ROCHE_ORDER_PRODUCT")
@Getter @Setter  @NoArgsConstructor
public class RocheOrderDetails {
	
	
	@Id
	@Column(name = "ORDER_ID")
	private UUID orderId;
	
	@Column(name = "PRODUCT_ID")
	private UUID productId;
	
	@Column(name = "PRODUCT_QUANTITY")
	private Integer quantity ;

	@Column(name = "PRODUCT_PRICE")
	private Double price ;
		
	@Transient
    public Double getTotalPrice() {
        return getPrice() * getQuantity();
    }

	public RocheOrderDetails(UUID orderId, UUID productId, Integer quantity, Double price) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}

    	
}
