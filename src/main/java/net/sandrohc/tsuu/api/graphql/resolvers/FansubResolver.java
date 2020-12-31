package net.sandrohc.tsuu.api.graphql.resolvers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.coxautodev.graphql.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import net.sandrohc.tsuu.api.graphql.loaders.FansubToReleasesDataLoader;
import net.sandrohc.tsuu.api.graphql.loaders.FansubToRevisionsDataLoader;
import net.sandrohc.tsuu.api.model.*;
import net.sandrohc.tsuu.api.graphql.types.FansubMediaType;
import net.sandrohc.tsuu.api.graphql.types.ImageType;
import net.sandrohc.tsuu.api.model.dto.FansubDTO;
import net.sandrohc.tsuu.api.model.dto.FansubRevisionDTO;

@SuppressWarnings("unused")
@Component
@RequiredArgsConstructor
public class FansubResolver implements GraphQLResolver<FansubDTO> {

	private final FansubToReleasesDataLoader fansubToReleasesDataLoader;
	private final FansubToRevisionsDataLoader fansubToRevisionsDataLoader;


	public String id(FansubDTO fansub) {
		return fansub.getId();
	}

	public CompletableFuture<List<Release>> releases(FansubDTO fansub) {
		return fansubToReleasesDataLoader.load(new ObjectId(fansub.getId()));
	}

	public List<FansubLink> links(FansubDTO fansub) {
		return fansub.getLinks();
	}

	public List<FansubMember> members(FansubDTO fansub) {
		return fansub.getMembers();
	}

	public FansubMediaType media(FansubDTO fansub) {
		return new FansubMediaType(
				new ImageType(fansub.getIconSmall(), fansub.getIconMedium(), fansub.getIconLarge()),
				new ImageType(fansub.getBannerSmall(), fansub.getBannerMedium(), fansub.getBannerLarge())
		);
	}

	public String description(FansubDTO fansub, boolean asHTML) {
		return fansub.getDescription(); // TODO: strip down HTML if 'asHTML' is false
	}

	public CompletableFuture<List<FansubRevisionDTO>> revisions(FansubDTO fansub, boolean onlyPending, boolean onlyMine) {
		return fansubToRevisionsDataLoader.load(new FansubToRevisionsDataLoader.Input(fansub.getId(), onlyPending, onlyMine));
	}

}
