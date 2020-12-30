package net.sandrohc.tsuu.api.graphql.resolvers;

import java.util.concurrent.CompletableFuture;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import net.sandrohc.tsuu.api.model.Fansub;
import net.sandrohc.tsuu.api.model.FansubRevision;
import net.sandrohc.tsuu.api.services.FansubService;

@Component
@RequiredArgsConstructor
public class MutationResolver implements GraphQLMutationResolver {

	private final FansubService fansubService;


	public CompletableFuture<Void> approveFansubRevision(ObjectId revisionId) {
		return fansubService.approveRevision(revisionId).toFuture();
	}

	public CompletableFuture<Void> rejectFansubRevision(ObjectId revisionId, String reason) {
		return fansubService.rejectRevision(revisionId, reason).toFuture();
	}

	public CompletableFuture<FansubRevision> saveFansubRevision(ObjectId revisionId, Fansub fansub) {
		return fansubService.saveRevision(revisionId, fansub).toFuture();
	}

}
