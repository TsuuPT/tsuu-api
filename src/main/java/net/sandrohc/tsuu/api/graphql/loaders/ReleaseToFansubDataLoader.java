package net.sandrohc.tsuu.api.graphql.loaders;

import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderOptions;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import net.sandrohc.tsuu.api.model.Fansub;
import net.sandrohc.tsuu.api.model.Release;
import net.sandrohc.tsuu.api.services.FansubService;

@Component
public class ReleaseToFansubDataLoader extends DataLoader<Release, Fansub> {

	@Autowired(required=false)
	public ReleaseToFansubDataLoader(FansubService fansubService) {
		super(batchLoader(fansubService));
	}

	public ReleaseToFansubDataLoader(FansubService fansubService, DataLoaderOptions options) {
		super(batchLoader(fansubService), options);
	}

	@NotNull
	private static BatchLoader<Release, Fansub> batchLoader(FansubService fansubService) {
		return releases -> Flux.fromIterable(releases)
				.flatMap(release -> fansubService.getById(release.getFansubId()))
				.collectList()
				.toFuture();
	}

}
