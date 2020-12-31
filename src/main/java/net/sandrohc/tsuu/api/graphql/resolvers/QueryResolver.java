package net.sandrohc.tsuu.api.graphql.resolvers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import net.sandrohc.tsuu.api.model.Media;
import net.sandrohc.tsuu.api.model.Release;
import net.sandrohc.tsuu.api.model.dto.FansubDTO;
import net.sandrohc.tsuu.api.model.mapper.FansubMapper;
import net.sandrohc.tsuu.api.services.FansubService;
import net.sandrohc.tsuu.api.services.MediaService;
import net.sandrohc.tsuu.api.services.ReleaseService;

@SuppressWarnings("unused")
@Component
@RequiredArgsConstructor
public class QueryResolver implements GraphQLQueryResolver {

	private final FansubService fansubService;
	private final MediaService mediaService;
	private final ReleaseService releaseService;

	private final FansubMapper fansubMapper;


	public CompletableFuture<List<FansubDTO>> fansubs() {
		return fansubService.getAll().map(fansubMapper::toDTO).collectList().toFuture();
	}

	public CompletableFuture<FansubDTO> fansub(String slug) {
		return fansubService.getBySlug(slug).map(fansubMapper::toDTO).toFuture();
	}


	public CompletableFuture<List<Media>> medias() {
		return mediaService.getAll().collectList().toFuture();
	}

	public CompletableFuture<Media> media(String slug) {
		return mediaService.getBySlug(slug).toFuture();
	}


	public CompletableFuture<List<Release>> releases(String fansubId) {
		return releaseService.getAllByFansubId(new ObjectId(fansubId)).collectList().toFuture();
	}

	public CompletableFuture<Release> release(String id) {
		return releaseService.getById(new ObjectId(id)).toFuture();
	}

}
