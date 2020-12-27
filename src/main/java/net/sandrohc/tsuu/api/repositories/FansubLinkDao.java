package net.sandrohc.tsuu.api.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import net.sandrohc.tsuu.api.model.FansubLink;

@Repository
public interface FansubLinkDao extends ReactiveCrudRepository<FansubLink, Long> {

	Flux<FansubLink> findByFansubId(Long fansubId);

}
