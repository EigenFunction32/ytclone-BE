package it.naoslab.ytclonebackend.repository;

import it.naoslab.ytclonebackend.model.RefreshToken;
import it.naoslab.ytclonebackend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {
    Optional<RefreshToken> findByToken(String token);
    String deleteByUser(User user);
}
