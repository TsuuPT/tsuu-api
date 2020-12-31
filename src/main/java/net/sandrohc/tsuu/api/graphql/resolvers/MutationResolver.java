package net.sandrohc.tsuu.api.graphql.resolvers;

import java.util.concurrent.CompletableFuture;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import net.sandrohc.tsuu.api.model.dto.FansubDTO;
import net.sandrohc.tsuu.api.model.dto.FansubRevisionDTO;
import net.sandrohc.tsuu.api.services.FansubService;

@SuppressWarnings("unused")
@Component
@RequiredArgsConstructor
public class MutationResolver implements GraphQLMutationResolver {

	private final FansubService fansubService;


	public CompletableFuture<Void> approveFansubRevision(String revisionId) {
		return fansubService.approveRevision(revisionId).toFuture();
	}

	public CompletableFuture<Void> rejectFansubRevision(String revisionId, String reason) {
		return fansubService.rejectRevision(revisionId, reason).toFuture();
	}

	public CompletableFuture<FansubRevisionDTO> saveFansubRevision(String revisionId, FansubDTO fansub) {
		return fansubService.saveRevision(revisionId, fansub).toFuture();
	}

}
