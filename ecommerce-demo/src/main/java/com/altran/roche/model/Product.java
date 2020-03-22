package com.altran.roche.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class Product {
    private UUID sku;  
    
    private String name;
    
    private Double price;
    
    private Integer quantity; 

  }
