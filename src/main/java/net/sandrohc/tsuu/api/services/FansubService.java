package net.sandrohc.tsuu.api.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import net.sandrohc.tsuu.api.model.Fansub;
import net.sandrohc.tsuu.api.model.dto.FansubDTO;
import net.sandrohc.tsuu.api.model.dto.FansubRevisionDTO;

// TODO: cache methods - https://stackoverflow.com/a/33426206/3220305
public interface FansubService {

	Flux<Fansub> getAll();

	Mono<Fansub> getById(String id);

	Mono<Fansub> getBySlug(String slug);

	Mono<Fansub> save(Fansub fansub);

	Flux<FansubRevisionDTO> getRevisions(String fansubId, boolean onlyPending, boolean onlyMine);

	Mono<FansubRevisionDTO> saveRevision(String revisionId, FansubDTO fansub);

	Mono<Void> approveRevision(String revisionId);

	Mono<Void> rejectRevision(String revisionId, String reason);
}
