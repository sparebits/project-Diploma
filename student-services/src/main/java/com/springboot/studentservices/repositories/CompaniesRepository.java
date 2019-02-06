package com.springboot.studentservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.studentservices.entities.Companies;

@Repository
public interface CompaniesRepository extends JpaRepository<Companies, Long> {

}