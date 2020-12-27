package net.sandrohc.tsuu.api.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import net.sandrohc.tsuu.api.model.Release;

public interface ReleaseService {

	Flux<Release> getAllByFansubId(Long fansubId);

	Mono<Release> getById(Long id);

	Mono<Release> save(Release release);

}
