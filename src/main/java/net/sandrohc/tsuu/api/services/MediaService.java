package net.sandrohc.tsuu.api.services;

import org.bson.types.ObjectId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import net.sandrohc.tsuu.api.model.Media;

// TODO: cache methods - https://stackoverflow.com/a/33426206/3220305
public interface MediaService {

	Flux<Media> getAll();

	Mono<Media> getById(ObjectId id);

	Mono<Media> getBySlug(String slug);

	Mono<Media> save(Media fansub);

}
