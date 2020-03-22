package com.altran.roche.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altran.roche.model.RocheProduct;

@Repository
public interface ProductRepository extends JpaRepository<RocheProduct, UUID> {

}
