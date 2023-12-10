package com.example.bestteamproject.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bestteamproject.dao.ServerRepository;
import com.example.bestteamproject.entity.Server;

@Service
public class ServerService {
    
    @Autowired
    private ServerRepository repository;

    public List<Server> findAll(){
        return repository.findAll();
    }

    public Server create(Server server){
        return repository.save(server);
    }

    public Boolean delete(Long id){
        Optional<Server> serverOptional = repository.findById(id);
        if(serverOptional.isPresent()){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}