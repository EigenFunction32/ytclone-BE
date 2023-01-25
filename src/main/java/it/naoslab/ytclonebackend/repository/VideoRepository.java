package it.naoslab.ytclonebackend.repository;

import it.naoslab.ytclonebackend.model.Video;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VideoRepository extends MongoRepository<Video, String> {

List<Video> findAllBy(TextCriteria criteria, Sort sort);
}
