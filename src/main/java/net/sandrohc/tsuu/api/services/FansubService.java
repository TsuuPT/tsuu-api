package net.sandrohc.tsuu.api.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import net.sandrohc.tsuu.api.model.Fansub;
import net.sandrohc.tsuu.api.model.FansubLink;
import net.sandrohc.tsuu.api.model.FansubMember;

public interface FansubService {

	Flux<Fansub> getAll();

	Mono<Fansub> getById(Long id);

	Mono<Fansub> getBySlug(String slug);

	Flux<FansubLink> getLinks(Long id);

	Flux<FansubMember> getMembers(Long id);

	Mono<Fansub> save(Fansub fansub);

}
