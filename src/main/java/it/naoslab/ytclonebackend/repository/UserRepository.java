package it.naoslab.ytclonebackend.repository;

import it.naoslab.ytclonebackend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmailAddress(String email);
    Optional<User> findBySub(String sub);
}
