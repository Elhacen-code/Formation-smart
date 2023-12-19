package com.example.bestteamproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bestteamproject.entity.Company;
import com.example.bestteamproject.entity.Server;
import java.util.List;


@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {
    public List<Server> findByCompany(Company company);
}