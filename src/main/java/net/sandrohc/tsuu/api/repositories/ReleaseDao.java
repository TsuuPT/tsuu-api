package net.sandrohc.tsuu.api.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import net.sandrohc.tsuu.api.model.Fansub;
import net.sandrohc.tsuu.api.model.Release;

@Repository
public interface ReleaseDao extends ReactiveCrudRepository<Release, Long> {

	Flux<Release> findAllByFansubIdOrderByTimestampDesc(Long fansubId);

}
