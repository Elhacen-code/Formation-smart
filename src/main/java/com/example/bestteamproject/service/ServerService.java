package com.example.bestteamproject.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.bestteamproject.entity.Server;

@Service
public interface ServerService {
    

    public List<Server> findAll();

    public Server create(Server server);

    public Boolean delete(Long id);
}