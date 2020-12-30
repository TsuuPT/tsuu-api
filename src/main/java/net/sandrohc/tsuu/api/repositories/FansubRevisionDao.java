package net.sandrohc.tsuu.api.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import net.sandrohc.tsuu.api.model.FansubRevision;
import net.sandrohc.tsuu.api.model.enums.RevisionStatus;

@Repository
public interface FansubRevisionDao extends ReactiveMongoRepository<FansubRevision, ObjectId> {

	@Query("{ 'fansub.id': ObjectId('?0') }")
	Flux<FansubRevision> findAllByFansubId(ObjectId fansubId);

	@Query("{ 'fansub.id': ObjectId('?0'), status: '?0' }")
	Flux<FansubRevision> findAllByFansubIdAndStatus(ObjectId fansubId, RevisionStatus status);

}
