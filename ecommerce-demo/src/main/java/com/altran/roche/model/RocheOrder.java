package com.altran.roche.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ROCHE_ORDER")
@Getter  @NoArgsConstructor 
public class RocheOrder { 
		
	@Id
	@Column(name= "ORDER_ID")
	private UUID orderId;
	
	@Column(name = "BUYER_EMAILID")
	private String buyerEmailId;
	
	@Column(name = "ORDER_CREATED_AT")
	@JsonFormat(pattern ="yyyy-MM-dd HH:mm")
	private LocalDateTime orderCreated ;

	public RocheOrder( String buyerEmailId) {
		super();
		this.orderId = UUID.randomUUID();
		this.buyerEmailId = buyerEmailId;
		this.orderCreated = LocalDateTime.now();
	}

}

