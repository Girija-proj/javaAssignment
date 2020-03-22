package com.altran.roche.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.altran.roche.model.RocheOrderDetails;

public interface OrderProductRepository extends JpaRepository<RocheOrderDetails, UUID> {
	
	@Query("FROM ROCHE_ORDER_PRODUCT WHERE ORDER_ID in :orders ")
	List<RocheOrderDetails> findOrders(@Param("orders") List<UUID> orders );
		
}
 