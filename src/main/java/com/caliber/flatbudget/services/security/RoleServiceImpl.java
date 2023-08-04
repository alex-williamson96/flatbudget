package com.caliber.flatbudget.services.security;

import com.caliber.flatbudget.models.security.ERole;
import com.caliber.flatbudget.models.security.Role;
import com.caliber.flatbudget.repositories.security.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(ERole role) {
        return roleRepository.findByName(role);
    }
}
