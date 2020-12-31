package net.sandrohc.tsuu.api.graphql.loaders;

import org.bson.types.ObjectId;
import org.dataloader.BatchLoader;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import net.sandrohc.tsuu.api.model.Media;
import net.sandrohc.tsuu.api.services.MediaService;

@Component
public class ReleaseToMediaDataLoader extends CachedDataLoader<ObjectId, Media> {

	public ReleaseToMediaDataLoader(MediaService mediaService) {
		super(batchLoader(mediaService));
	}

	@NotNull
	private static BatchLoader<ObjectId, Media> batchLoader(MediaService mediaService) {
		return mediaIds -> Flux.fromIterable(mediaIds)
				.flatMap(mediaService::getById)
				.collectList()
				.toFuture();
	}

}
