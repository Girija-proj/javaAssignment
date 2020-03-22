package com.altran.roche.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.altran.roche.model.RocheOrder;

public interface OrderRepository extends JpaRepository<RocheOrder, UUID> {
 
	@Query("FROM ROCHE_ORDER WHERE  ORDER_CREATED_AT > :fromDate AND  ORDER_CREATED_AT <:toDate ")
	List<RocheOrder> findOrderIdByDateRange(@Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate);
	
}
