package com.example.bestteamproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bestteamproject.entity.Server;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {
    
}