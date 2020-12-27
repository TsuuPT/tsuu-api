package net.sandrohc.tsuu.api.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import net.sandrohc.tsuu.api.model.Media;

@Repository
public interface MediaDao extends ReactiveCrudRepository<Media, Long> {

	Mono<Media> findBySlug(String slug);

}
