package it.naoslab.ytclonebackend.repository;

import it.naoslab.ytclonebackend.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Video, String> {
}
