package com.altran.roche.model;

import java.util.List;
import com.altran.roche.model.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Orders {
	
	private List<Product> products ;
	
	private String buyerEmailId;

}
