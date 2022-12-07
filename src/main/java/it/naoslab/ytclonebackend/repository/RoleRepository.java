package it.naoslab.ytclonebackend.repository;


import it.naoslab.ytclonebackend.model.ERole;
import it.naoslab.ytclonebackend.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}