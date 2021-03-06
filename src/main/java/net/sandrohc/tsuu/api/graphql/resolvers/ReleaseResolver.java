package net.sandrohc.tsuu.api.graphql.resolvers;

import java.util.concurrent.CompletableFuture;

import com.coxautodev.graphql.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import net.sandrohc.tsuu.api.graphql.loaders.ReleaseToFansubDataLoader;
import net.sandrohc.tsuu.api.graphql.loaders.ReleaseToMediaDataLoader;
import net.sandrohc.tsuu.api.model.Media;
import net.sandrohc.tsuu.api.model.Release;
import net.sandrohc.tsuu.api.model.dto.FansubDTO;

@SuppressWarnings("unused")
@Component
@RequiredArgsConstructor
public class ReleaseResolver implements GraphQLResolver<Release> {

	private final ReleaseToFansubDataLoader releaseToFansubDataLoader;
	private final ReleaseToMediaDataLoader releaseToMediaDataLoader;


	public String id(Release release) {
		return release.getId().toHexString();
	}

	public CompletableFuture<FansubDTO> fansub(Release release) {
		return releaseToFansubDataLoader.load(release.getFansubId().toHexString());
	}

	public CompletableFuture<Media> media(Release release) {
		return releaseToMediaDataLoader.load(release.getMediaId());
	}

}
