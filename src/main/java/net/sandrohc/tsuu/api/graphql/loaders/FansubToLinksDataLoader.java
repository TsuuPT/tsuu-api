package net.sandrohc.tsuu.api.graphql.loaders;

import java.util.List;

import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderOptions;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import net.sandrohc.tsuu.api.model.Fansub;
import net.sandrohc.tsuu.api.model.FansubLink;
import net.sandrohc.tsuu.api.services.FansubService;

@Component
public class FansubToLinksDataLoader extends DataLoader<Fansub, List<FansubLink>> {

	@Autowired(required=false)
	public FansubToLinksDataLoader(FansubService fansubService) {
		super(batchLoader(fansubService));
	}

	public FansubToLinksDataLoader(FansubService fansubService, DataLoaderOptions options) {
		super(batchLoader(fansubService), options);
	}

	@NotNull
	private static BatchLoader<Fansub, List<FansubLink>> batchLoader(FansubService fansubService) {
		return fansubs -> Flux.fromIterable(fansubs)
				.flatMap(fansub -> fansubService.getLinks(fansub.getId()).collectList())
				.collectList()
				.toFuture();
	}

}
