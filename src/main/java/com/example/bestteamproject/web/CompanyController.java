package com.example.bestteamproject.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bestteamproject.entity.Company;
import com.example.bestteamproject.service.CompanyService;
import com.example.bestteamproject.utils.Constants;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(Constants.APP_ROOT+Constants.COMPANY)
public class CompanyController {
    
    @Autowired
    private CompanyService service;

    @PostMapping
    public ResponseEntity<Company> create(@RequestBody Company company ){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(company));
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompany(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyByID(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCompany(@RequestBody Company company, @PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(company, id));
    }

    @GetMapping("/company-servers/{idCompany}")
    public ResponseEntity<Object> getCompanyServers(@PathVariable Long idCompany){
        return service.getCompanyServers(idCompany);
    }
}