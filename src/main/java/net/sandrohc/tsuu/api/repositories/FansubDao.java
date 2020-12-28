package net.sandrohc.tsuu.api.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import net.sandrohc.tsuu.api.model.Fansub;

@Repository
public interface FansubDao extends ReactiveMongoRepository<Fansub, ObjectId> {

	Mono<Fansub> findBySlug(String slug);

}
