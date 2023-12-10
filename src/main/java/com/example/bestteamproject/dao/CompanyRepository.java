package com.example.bestteamproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.bestteamproject.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
    
}