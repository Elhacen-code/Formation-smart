package com.example.bestteamproject.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.bestteamproject.entity.Company;

@Service
public interface CompanyService {


    public List<Company> findAll();

    public Company create(Company company);

    public Boolean delete(Long id);

    public Company findById(Long id);

    public String update(Company company, Long id);

    public List<Company> findByName(String name);

    public ResponseEntity<Object> getCompanyServers(Long idCompany);
}