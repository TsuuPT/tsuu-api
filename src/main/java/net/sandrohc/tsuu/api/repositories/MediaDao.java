package net.sandrohc.tsuu.api.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import net.sandrohc.tsuu.api.model.Media;

@Repository
public interface MediaDao extends ReactiveMongoRepository<Media, ObjectId> {

	Mono<Media> findBySlug(String slug);

}
