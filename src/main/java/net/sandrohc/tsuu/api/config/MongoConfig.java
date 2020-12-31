package net.sandrohc.tsuu.api.config;

import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.ReactiveIndexOperations;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import net.sandrohc.tsuu.api.model.Fansub;
import net.sandrohc.tsuu.api.model.FansubRevision;
import net.sandrohc.tsuu.api.model.Media;
import net.sandrohc.tsuu.api.model.Release;

@Configuration
@RequiredArgsConstructor
@EnableReactiveMongoRepositories(basePackages = "net.sandrohc.tsuu.api.repositories")
public class MongoConfig {

	private final ReactiveMongoTemplate reactiveMongoTemplate;

	@PostConstruct
	public void initIndexes() {
		createCollections(Fansub.class, FansubRevision.class, Media.class, Release.class);

		ReactiveIndexOperations fansubOps = reactiveMongoTemplate.indexOps(Fansub.class);
		fansubOps.ensureIndex(new Index().on("slug", Sort.Direction.ASC).unique()).subscribe();

		ReactiveIndexOperations fansubRevisionOps = reactiveMongoTemplate.indexOps(FansubRevision.class);
		fansubRevisionOps.ensureIndex(new Index().on("status", Sort.Direction.ASC)).subscribe();
		fansubRevisionOps.ensureIndex(new Index().on("createdAt", Sort.Direction.ASC)).subscribe();
		fansubRevisionOps.ensureIndex(new Index().on("fansub._id", Sort.Direction.ASC)).subscribe();

		ReactiveIndexOperations mediaOps = reactiveMongoTemplate.indexOps(Media.class);
		mediaOps.ensureIndex(new Index().on("slug", Sort.Direction.ASC).on("type", Sort.Direction.ASC).unique()).subscribe();
		mediaOps.ensureIndex(new Index().on("type", Sort.Direction.ASC)).subscribe();

		ReactiveIndexOperations releaseOps = reactiveMongoTemplate.indexOps(Release.class);
		releaseOps.ensureIndex(new Index().on("timestamp", Sort.Direction.ASC)).subscribe();
		releaseOps.ensureIndex(new Index().on("fansubId", Sort.Direction.ASC)).subscribe();
		releaseOps.ensureIndex(new Index().on("mediaId", Sort.Direction.ASC)).subscribe();
	}

	private void createCollections(Class<?>... classes) {
		for (Class<?> clazz : classes) {
			reactiveMongoTemplate.collectionExists(clazz)
					.filter(exists -> !exists)
					.flatMap(i -> reactiveMongoTemplate.createCollection(clazz, CollectionOptions.just(Collation.of("pt"))))
					.block();
		}
	}


}
