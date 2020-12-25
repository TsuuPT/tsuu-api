package net.sandrohc.tsuu.api.config;

import java.util.List;

import graphql.execution.instrumentation.Instrumentation;
import graphql.execution.instrumentation.dataloader.DataLoaderDispatcherInstrumentation;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfig {

	@Bean
	DataLoaderRegistry dataLoaderRegistry(List<DataLoader<?, ?>> loaderList) {
		DataLoaderRegistry registry = new DataLoaderRegistry();
		for (DataLoader<?, ?> loader : loaderList) {
			registry.register(loader.getClass().getSimpleName(), loader);
		}
		return registry;
	}

	@Bean
	Instrumentation instrumentation(DataLoaderRegistry dataLoaderRegistry) {
		return new DataLoaderDispatcherInstrumentation(dataLoaderRegistry);
	}

}
