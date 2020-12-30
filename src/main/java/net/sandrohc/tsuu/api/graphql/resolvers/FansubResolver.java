package net.sandrohc.tsuu.api.graphql.resolvers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.coxautodev.graphql.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import net.sandrohc.tsuu.api.graphql.loaders.FansubToReleasesDataLoader;
import net.sandrohc.tsuu.api.graphql.loaders.FansubToRevisionsDataLoader;
import net.sandrohc.tsuu.api.model.*;
import net.sandrohc.tsuu.api.graphql.types.FansubMediaType;
import net.sandrohc.tsuu.api.graphql.types.ImageType;

@SuppressWarnings("unused")
@Component
@RequiredArgsConstructor
public class FansubResolver implements GraphQLResolver<Fansub> {

	private final FansubToReleasesDataLoader fansubToReleasesDataLoader;
	private final FansubToRevisionsDataLoader fansubToRevisionsDataLoader;


	public String id(Fansub fansub) {
		return fansub.getId().toHexString();
	}

	public CompletableFuture<List<Release>> releases(Fansub fansub) {
		return fansubToReleasesDataLoader.load(fansub);
	}

	public List<FansubLink> links(Fansub fansub) {
		return fansub.getLinks(); // TODO: use CompletableFuture?
	}

	public List<FansubMember> members(Fansub fansub) {
		return fansub.getMembers(); // TODO: use CompletableFuture?
	}

	public FansubMediaType media(Fansub fansub) {
		return new FansubMediaType(
				new ImageType(fansub.getIconSmall(), fansub.getIconMedium(), fansub.getIconLarge()),
				new ImageType(fansub.getBannerSmall(), fansub.getBannerMedium(), fansub.getBannerLarge())
		);
	}

	public String description(Fansub fansub, boolean asHTML) {
		return fansub.getDescription(); // TODO: strip down HTML if 'asHTML' is false
	}

	public CompletableFuture<List<FansubRevision>> revisions(Fansub fansub, boolean onlyPending, boolean onlyMine) {
		return fansubToRevisionsDataLoader.load(new FansubToRevisionsDataLoader.Input(fansub.getId(), onlyPending, onlyMine));
	}

}
