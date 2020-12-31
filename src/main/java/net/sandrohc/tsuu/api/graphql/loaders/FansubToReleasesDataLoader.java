package net.sandrohc.tsuu.api.graphql.loaders;

import java.util.List;

import org.bson.types.ObjectId;
import org.dataloader.BatchLoader;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import net.sandrohc.tsuu.api.model.Release;
import net.sandrohc.tsuu.api.services.ReleaseService;

@Component
public class FansubToReleasesDataLoader extends CachedDataLoader<ObjectId, List<Release>> {

	public FansubToReleasesDataLoader(ReleaseService releaseService) {
		super(batchLoader(releaseService));
	}

	@NotNull
	private static BatchLoader<ObjectId, List<Release>> batchLoader(ReleaseService releaseService) {
		return fansubIds -> Flux.fromIterable(fansubIds)
				.flatMap(fansubId -> releaseService.getAllByFansubId(fansubId).collectList())
				.collectList()
				.toFuture();
	}

}
