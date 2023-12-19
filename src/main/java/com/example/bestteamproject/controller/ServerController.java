package com.example.bestteamproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bestteamproject.entity.Company;
import com.example.bestteamproject.entity.Server;
import com.example.bestteamproject.service.ServerService;

@RestController
@RequestMapping("/api/server")
public class ServerController {
    
    @Autowired
    private ServerService service;

   
    @PostMapping("/create")
    public ResponseEntity<Server> create(@RequestBody Server server ){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(server));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Server>> getAllServers(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<Server> getCompanyByID(@RequestBody Server server, @PathVariable("id") Long id){

    // }

}