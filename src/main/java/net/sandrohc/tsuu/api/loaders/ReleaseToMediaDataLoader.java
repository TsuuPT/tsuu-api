package net.sandrohc.tsuu.api.loaders;

import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderOptions;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import net.sandrohc.tsuu.api.model.Fansub;
import net.sandrohc.tsuu.api.model.Media;
import net.sandrohc.tsuu.api.model.Release;
import net.sandrohc.tsuu.api.services.FansubService;
import net.sandrohc.tsuu.api.services.MediaService;

@Component
public class ReleaseToMediaDataLoader extends DataLoader<Release, Media> {

	@Autowired(required=false)
	public ReleaseToMediaDataLoader(MediaService mediaService) {
		super(batchLoader(mediaService));
	}

	public ReleaseToMediaDataLoader(MediaService mediaService, DataLoaderOptions options) {
		super(batchLoader(mediaService), options);
	}

	@NotNull
	private static BatchLoader<Release, Media> batchLoader(MediaService mediaService) {
		return releases -> Flux.fromIterable(releases)
				.flatMap(release -> mediaService.getById(release.getId()).flux())
				.collectList()
				.toFuture();
	}

}
