package com.tornikeperadze.blog.service.impl;

import com.tornikeperadze.blog.model.Role;
import com.tornikeperadze.blog.repository.RoleRepository;
import com.tornikeperadze.blog.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
