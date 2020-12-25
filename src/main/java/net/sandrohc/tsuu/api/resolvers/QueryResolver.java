package net.sandrohc.tsuu.api.resolvers;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import net.sandrohc.tsuu.api.model.Fansub;
import net.sandrohc.tsuu.api.model.Media;
import net.sandrohc.tsuu.api.model.Release;
import net.sandrohc.tsuu.api.services.FansubService;
import net.sandrohc.tsuu.api.services.MediaService;
import net.sandrohc.tsuu.api.services.ReleaseService;

@Component
@RequiredArgsConstructor
public class QueryResolver implements GraphQLQueryResolver {

	private final FansubService fansubService;
	private final ReleaseService releaseService;
	private final MediaService mediaService;


	public List<Fansub> fansubs() {
		return fansubService.getAll().collectList().block();
	}

	public List<Media> media() {
		return mediaService.getAll().collectList().block();
	}

	public List<Release> releases(Long fansubId) {
		return releaseService.getAllByFansubId(fansubId).collectList().block();
	}

}
