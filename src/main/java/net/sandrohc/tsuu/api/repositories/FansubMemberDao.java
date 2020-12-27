package net.sandrohc.tsuu.api.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import net.sandrohc.tsuu.api.model.FansubMember;

@Repository
public interface FansubMemberDao extends ReactiveCrudRepository<FansubMember, Long> {

	Flux<FansubMember> findByFansubId(Long fansubId);

}
