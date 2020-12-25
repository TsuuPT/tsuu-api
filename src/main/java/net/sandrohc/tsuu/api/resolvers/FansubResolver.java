package net.sandrohc.tsuu.api.resolvers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.coxautodev.graphql.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import net.sandrohc.tsuu.api.loaders.FansubToReleasesDataLoader;
import net.sandrohc.tsuu.api.model.Fansub;
import net.sandrohc.tsuu.api.model.Release;
import net.sandrohc.tsuu.api.resolvers.types.FansubMediaType;
import net.sandrohc.tsuu.api.resolvers.types.ImageType;

@Component
@RequiredArgsConstructor
public class FansubResolver implements GraphQLResolver<Fansub> {

	private final FansubToReleasesDataLoader fansubToReleasesDataLoader;


	public CompletableFuture<List<Release>> releases(Fansub fansub) {
		return fansubToReleasesDataLoader.load(fansub);
	}

	public FansubMediaType media(Fansub fansub) {
		return new FansubMediaType(
				new ImageType(fansub.getIconSmall(), fansub.getIconMedium(), fansub.getIconLarge()),
				new ImageType(fansub.getBannerSmall(), fansub.getBannerMedium(), fansub.getBannerLarge())
		);
	}

}
