package net.sandrohc.tsuu.api.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import net.sandrohc.tsuu.api.model.Fansub;
import net.sandrohc.tsuu.api.services.FansubService;

@Component
@RequiredArgsConstructor
public class MutationResolver implements GraphQLMutationResolver {

	private final FansubService fansubService;


	public Fansub createFansub(Fansub fansub) {
		return fansubService.save(fansub).block();
	}

}
