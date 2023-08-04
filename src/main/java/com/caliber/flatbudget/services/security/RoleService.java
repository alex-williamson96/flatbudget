package com.caliber.flatbudget.services.security;

import com.caliber.flatbudget.models.security.ERole;
import com.caliber.flatbudget.models.security.Role;

import java.util.Optional;

public interface RoleService {

    Optional<Role> findByName(ERole role);
}
