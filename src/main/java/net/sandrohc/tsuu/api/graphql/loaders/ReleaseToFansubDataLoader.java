package net.sandrohc.tsuu.api.graphql.loaders;

import org.dataloader.BatchLoader;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import net.sandrohc.tsuu.api.model.dto.FansubDTO;
import net.sandrohc.tsuu.api.model.mapper.FansubMapper;
import net.sandrohc.tsuu.api.services.FansubService;

@Component
public class ReleaseToFansubDataLoader extends CachedDataLoader<String, FansubDTO> {

	public ReleaseToFansubDataLoader(FansubService fansubService, FansubMapper fansubMapper) {
		super(batchLoader(fansubService, fansubMapper));
	}

	@NotNull
	private static BatchLoader<String, FansubDTO> batchLoader(FansubService fansubService, FansubMapper fansubMapper) {
		return fansubIds -> Flux.fromIterable(fansubIds)
				.flatMap(fansubService::getById)
				.map(fansubMapper::toDTO)
				.collectList()
				.toFuture();
	}

}
