package net.sandrohc.tsuu.api.graphql.loaders;

import java.util.List;

import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderOptions;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import net.sandrohc.tsuu.api.model.Fansub;
import net.sandrohc.tsuu.api.model.Release;
import net.sandrohc.tsuu.api.services.ReleaseService;

@Component
public class FansubToReleasesDataLoader extends DataLoader<Fansub, List<Release>> {

	@Autowired(required=false)
	public FansubToReleasesDataLoader(ReleaseService releaseService) {
		super(batchLoader(releaseService));
	}

	public FansubToReleasesDataLoader(ReleaseService releaseService, DataLoaderOptions options) {
		super(batchLoader(releaseService), options);
	}

	@NotNull
	private static BatchLoader<Fansub, List<Release>> batchLoader(ReleaseService releaseService) {
		return fansubs -> Flux.fromIterable(fansubs)
				.flatMap(fansub -> releaseService.getAllByFansubId(fansub.getId()).collectList())
				.collectList()
				.toFuture();
	}

}
