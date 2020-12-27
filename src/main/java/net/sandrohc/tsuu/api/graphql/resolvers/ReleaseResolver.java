package net.sandrohc.tsuu.api.graphql.resolvers;

import java.util.concurrent.CompletableFuture;

import com.coxautodev.graphql.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import net.sandrohc.tsuu.api.graphql.loaders.ReleaseToFansubDataLoader;
import net.sandrohc.tsuu.api.graphql.loaders.ReleaseToMediaDataLoader;
import net.sandrohc.tsuu.api.model.Fansub;
import net.sandrohc.tsuu.api.model.Media;
import net.sandrohc.tsuu.api.model.Release;
import net.sandrohc.tsuu.api.services.FansubService;
import net.sandrohc.tsuu.api.services.MediaService;

@Component
@RequiredArgsConstructor
public class ReleaseResolver implements GraphQLResolver<Release> {

	private final ReleaseToFansubDataLoader releaseToFansubDataLoader;
	private final ReleaseToMediaDataLoader releaseToMediaDataLoader;

	private final FansubService fansubService;
	private final MediaService mediaService;


	public CompletableFuture<Fansub> fansub(Release release) {
//		return fansubService.getById(release.getFansubId()).toFuture();
		return releaseToFansubDataLoader.load(release);
	}

	public CompletableFuture<Media> media(Release release) {
//		return mediaService.getById(release.getMediaId()).toFuture();
		return releaseToMediaDataLoader.load(release); // TODO: confirm why this doesn't work
	}

	public String description(Release release, boolean asHTML) {
		return asHTML ? release.getDescriptionHTML() : release.getDescription();
	}

}
