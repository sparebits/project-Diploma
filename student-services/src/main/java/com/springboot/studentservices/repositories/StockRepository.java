package com.springboot.studentservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.studentservices.entities.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {

}
