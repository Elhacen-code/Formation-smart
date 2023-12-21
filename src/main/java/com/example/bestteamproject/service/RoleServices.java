package com.example.bestteamproject.service;

import java.util.List;
import com.example.bestteamproject.entity.Role;


public interface RoleServices {
    
    public List<Role> getAllRoles();

    public Role getRoleById(long id);

    public Role createRole(Role role);

    public Role updateRole(Role updatedRole);
    
    public void deleteRole(long id);
}