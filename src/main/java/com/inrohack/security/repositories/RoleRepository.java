package com.inrohack.security.repositories;

import com.inrohack.security.models.ERole;
import com.inrohack.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(ERole name);
}
