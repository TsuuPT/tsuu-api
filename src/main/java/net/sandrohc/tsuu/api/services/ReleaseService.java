package net.sandrohc.tsuu.api.services;

import org.bson.types.ObjectId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import net.sandrohc.tsuu.api.model.Release;

// TODO: cache methods - https://stackoverflow.com/a/33426206/3220305
public interface ReleaseService {

	Flux<Release> getAllByFansubId(ObjectId fansubId);

	Mono<Release> getById(ObjectId id);

	Mono<Release> save(Release release);

	Flux<Release> saveAll(Iterable<Release> releases);

}
