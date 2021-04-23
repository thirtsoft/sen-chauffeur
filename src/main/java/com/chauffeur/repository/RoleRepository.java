package com.chauffeur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chauffeur.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
