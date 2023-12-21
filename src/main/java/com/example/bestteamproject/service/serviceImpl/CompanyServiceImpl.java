package com.example.bestteamproject.service.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.bestteamproject.dao.CompanyRepository;
import com.example.bestteamproject.dao.ServerRepository;
import com.example.bestteamproject.dto.CompanyServerDto;
import com.example.bestteamproject.entity.Company;
import com.example.bestteamproject.entity.Server;
import com.example.bestteamproject.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyRepository repository;

    @Autowired
    private ServerRepository serverRepository;

    @Override
    public List<Company> findAll() {
        return repository.findAll();
    }

    @Override
    public Company create(Company company) {
        return repository.save(company);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Company> companyOptional = repository.findById(id);
        if (companyOptional.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Company findById(Long id) {
        Optional<Company> companyOptional = repository.findById(id);
        if (companyOptional.isPresent())
            return companyOptional.get();
        return null;
    }

    @Override
    public String update(Company company, Long id) {
        Optional<Company> companyOptional = repository.findById(id);
        if (companyOptional.isPresent()) {
            Company updatedCompany = companyOptional.get();

            updatedCompany.setName(company.getName());

            repository.save(updatedCompany);
            return "Company updated !";

        }
        return "Old company not found !";
    }

    @Override
    public List<Company> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public ResponseEntity<Object> getCompanyServers(Long idCompany) {
        Optional<Company> optionalCompany = repository.findById(idCompany);
        if (optionalCompany.isPresent()) {
            var company = optionalCompany.get();

            List<Server> companyServers = serverRepository.findByCompany(company);
            var companyServerDto = new CompanyServerDto(companyServers.size(), companyServers);

            return ResponseEntity.ok().body(companyServerDto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
    }
}