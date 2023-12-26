package com.example.bestteamproject;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.bestteamproject.dao.CompanyRepository;
import com.example.bestteamproject.dao.ServerRepository;
import com.example.bestteamproject.dto.CompanyServerDto;
import com.example.bestteamproject.entity.Company;
import com.example.bestteamproject.entity.Server;
import com.example.bestteamproject.service.serviceImpl.CompanyServiceImpl;

public class CompanyServiceTest {

    @Test
    public void testGetCompanyServers() {

        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        ServerRepository serverRepository = Mockito.mock(ServerRepository.class);

        // Create an instance of your service class and inject the mock repositories
        CompanyServiceImpl companyService = new CompanyServiceImpl(companyRepository, serverRepository);

        // Arrange
        Long companyId = 1L;

        // Create a sample company and server list
        Company company = new Company();
        company.setId(companyId);

        Server server1 = new Server();
        server1.setId(1L);
        server1.setCompany(company);

        Server server2 = new Server();
        server2.setId(2L);
        server2.setCompany(company);

        List<Server> companyServers = Arrays.asList(server1, server2);

        // Mock the behavior of your repositories
        when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));
        when(serverRepository.findByCompany(company)).thenReturn(companyServers);

        // Act
        ResponseEntity<Object> response = companyService.getCompanyServers(companyId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertTrue(response.getBody() instanceof CompanyServerDto);
        CompanyServerDto companyServerDto = (CompanyServerDto) response.getBody();
        assertEquals(companyServers.size(), companyServerDto.getTotslServers());
        assertEquals(companyServers, companyServerDto.getServers());

        // Verify that repository methods were called with the correct parameters
        verify(companyRepository, times(1)).findById(companyId);
        verify(serverRepository, times(1)).findByCompany(company);
    }
}
