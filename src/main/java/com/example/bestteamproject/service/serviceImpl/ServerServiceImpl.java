package com.example.bestteamproject.service.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bestteamproject.dao.ServerRepository;
import com.example.bestteamproject.entity.Server;
import com.example.bestteamproject.service.ServerService;

@Service
public class ServerServiceImpl implements ServerService{

    @Autowired
    private ServerRepository repository;

    @Override
    public List<Server> findAll() {
        return repository.findAll();
    }

    @Override
    public Server create(Server server) {
        return repository.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Server> serverOptional = repository.findById(id);
        if (serverOptional.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}