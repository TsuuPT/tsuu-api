package net.sandrohc.tsuu.api.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import net.sandrohc.tsuu.api.model.Fansub;

public interface FansubService {

	Flux<Fansub> getAll();

	Mono<Fansub> getById(Long id);

	Mono<Fansub> save(Fansub fansub);

}
