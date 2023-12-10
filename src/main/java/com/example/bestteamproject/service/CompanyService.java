package com.example.bestteamproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bestteamproject.dao.CompanyRepository;
import com.example.bestteamproject.entity.Company;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;

    public List<Company> findAll(){
        return repository.findAll();
    }

    public Company create(Company company){
        return repository.save(company);
    }

    public Boolean delete(Long id){
        Optional<Company> companyOptional = repository.findById(id);
        if(companyOptional.isPresent()){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}