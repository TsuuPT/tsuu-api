package net.sandrohc.tsuu.api.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import net.sandrohc.tsuu.api.model.Media;

public interface MediaService {

	Flux<Media> getAll();

	Mono<Media> getById(Long id);

	Mono<Media> save(Media fansub);

}
