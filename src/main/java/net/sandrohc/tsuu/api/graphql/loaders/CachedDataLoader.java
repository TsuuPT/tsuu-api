package net.sandrohc.tsuu.api.graphql.loaders;

import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.dataloader.BatchLoader;
import org.dataloader.CacheMap;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderOptions;

abstract class CachedDataLoader<I,O> extends DataLoader<I,O> {

	public CachedDataLoader(BatchLoader<I,O> batchLoader) {
		super(batchLoader, DataLoaderOptions.newOptions().setCacheMap(new CaffeineCacheMap<>()));
	}


	private static class CaffeineCacheMap<I,O> implements CacheMap<I,O> {

		private final Cache<I,O> cache;

		public CaffeineCacheMap() {
			this.cache = Caffeine.newBuilder()
					.expireAfterWrite(60,TimeUnit.MINUTES)
					.maximumSize(1000)
					.build();
		}

		@Override
		public boolean containsKey(I key) {
			return cache.getIfPresent(key) != null;
		}

		@Override
		public O get(I key) {
			return cache.getIfPresent(key);
		}

		@Override
		public CacheMap<I,O> set(I key, O value) {
			cache.put(key, value);
			return this;
		}

		@Override
		public CacheMap<I,O> delete(I key) {
			cache.invalidate(key);
			return this;
		}

		@Override
		public CacheMap<I,O> clear() {
			cache.invalidateAll();
			return this;
		}
	}

}
