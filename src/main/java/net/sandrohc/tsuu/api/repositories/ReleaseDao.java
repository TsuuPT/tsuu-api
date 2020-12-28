package net.sandrohc.tsuu.api.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import net.sandrohc.tsuu.api.model.Release;

@Repository
public interface ReleaseDao extends ReactiveMongoRepository<Release, ObjectId> {

	@Query("{ 'fansubId': ObjectId('?0') }")
	Flux<Release> findAllByFansubId(ObjectId id);

	@Query("{ 'mediaId': ObjectId('?0') }")
	Flux<Release> findAllByMediaId(ObjectId id);

}
