package net.sandrohc.tsuu.api.graphql.resolvers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.coxautodev.graphql.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import net.sandrohc.tsuu.api.graphql.loaders.FansubToLinksDataLoader;
import net.sandrohc.tsuu.api.graphql.loaders.FansubToMembersDataLoader;
import net.sandrohc.tsuu.api.graphql.loaders.FansubToReleasesDataLoader;
import net.sandrohc.tsuu.api.model.Fansub;
import net.sandrohc.tsuu.api.model.FansubLink;
import net.sandrohc.tsuu.api.model.FansubMember;
import net.sandrohc.tsuu.api.model.Release;
import net.sandrohc.tsuu.api.graphql.types.FansubMediaType;
import net.sandrohc.tsuu.api.graphql.types.ImageType;

@Component
@RequiredArgsConstructor
public class FansubResolver implements GraphQLResolver<Fansub> {

	private final FansubToReleasesDataLoader fansubToReleasesDataLoader;
	private final FansubToLinksDataLoader fansubToLinksDataLoader;
	private final FansubToMembersDataLoader fansubToMembersDataLoader;


	public CompletableFuture<List<Release>> releases(Fansub fansub) {
		return fansubToReleasesDataLoader.load(fansub);
	}

	public CompletableFuture<List<FansubLink>> links(Fansub fansub) {
		return fansubToLinksDataLoader.load(fansub);
	}

	public CompletableFuture<List<FansubMember>> members(Fansub fansub) {
		return fansubToMembersDataLoader.load(fansub);
	}

	public FansubMediaType media(Fansub fansub) {
		return new FansubMediaType(
				new ImageType(fansub.getIconSmall(), fansub.getIconMedium(), fansub.getIconLarge()),
				new ImageType(fansub.getBannerSmall(), fansub.getBannerMedium(), fansub.getBannerLarge())
		);
	}

}
