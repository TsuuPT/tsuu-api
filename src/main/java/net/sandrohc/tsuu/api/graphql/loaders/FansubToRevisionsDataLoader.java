package net.sandrohc.tsuu.api.graphql.loaders;

import java.util.List;

import lombok.Data;
import org.dataloader.BatchLoader;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import net.sandrohc.tsuu.api.graphql.loaders.FansubToRevisionsDataLoader.Input;
import net.sandrohc.tsuu.api.model.dto.FansubRevisionDTO;
import net.sandrohc.tsuu.api.services.FansubService;

@Component
public class FansubToRevisionsDataLoader extends CachedDataLoader<Input, List<FansubRevisionDTO>> {

	public FansubToRevisionsDataLoader(FansubService fansubService) {
		super(batchLoader(fansubService));
	}

	@NotNull
	private static BatchLoader<Input, List<FansubRevisionDTO>> batchLoader(FansubService fansubService) {
		return fansubInputs -> Flux.fromIterable(fansubInputs)
				.flatMap(input -> fansubService.getRevisions(input.getFansubId(), input.isOnlyPending(), input.isOnlyMine()).collectList())
				.collectList()
				.toFuture();
	}

	@Data
	public static class Input {
		public final String fansubId;
		public final boolean onlyPending;
		public final boolean onlyMine;
	}

}
