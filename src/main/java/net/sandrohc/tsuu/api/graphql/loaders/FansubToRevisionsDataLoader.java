package net.sandrohc.tsuu.api.graphql.loaders;

import java.util.List;

import lombok.Data;
import org.bson.types.ObjectId;
import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderOptions;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import net.sandrohc.tsuu.api.graphql.loaders.FansubToRevisionsDataLoader.Input;
import net.sandrohc.tsuu.api.model.FansubRevision;
import net.sandrohc.tsuu.api.services.FansubService;

@Component
public class FansubToRevisionsDataLoader extends DataLoader<Input, List<FansubRevision>> {

	@Autowired
	public FansubToRevisionsDataLoader(FansubService fansubService) {
		super(batchLoader(fansubService));
	}

	public FansubToRevisionsDataLoader(FansubService fansubService, DataLoaderOptions options) {
		super(batchLoader(fansubService), options);
	}

	@NotNull
	private static BatchLoader<Input, List<FansubRevision>> batchLoader(FansubService fansubService) {
		return fansubInputs -> Flux.fromIterable(fansubInputs)
				.flatMap(input -> fansubService.getRevisions(input.getFansubId(), input.isOnlyPending(), input.isOnlyMine()).collectList())
				.collectList()
				.toFuture();
	}


	@Data
	public static class Input {
		public final ObjectId fansubId;
		public final boolean onlyPending;
		public final boolean onlyMine;
	}

}
