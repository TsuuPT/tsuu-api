package net.sandrohc.tsuu.api.services;

import org.bson.types.ObjectId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import net.sandrohc.tsuu.api.model.Fansub;
import net.sandrohc.tsuu.api.model.FansubLink;
import net.sandrohc.tsuu.api.model.FansubMember;

// TODO: cache methods - https://stackoverflow.com/a/33426206/3220305
public interface FansubService {

	Flux<Fansub> getAll();

	Mono<Fansub> getById(ObjectId id);

	Mono<Fansub> getBySlug(String slug);

	Mono<Fansub> save(Fansub fansub);

}
