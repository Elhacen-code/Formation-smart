package com.example.bestteamproject.dto;

import java.util.List;
import com.example.bestteamproject.entity.Server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyServerDto {
    
    private int totslServers;
    private List<Server> servers;
}
