package net.sandrohc.tsuu.api.config;

import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Configuration
@EnableCaching
public class CacheConfig {

	@Bean
	public Caffeine caffeineConfig() {
		return Caffeine.newBuilder()
				.expireAfterWrite(60, TimeUnit.MINUTES)
				.maximumSize(1000);
	}

	@Bean
	public CacheManager cacheManager(Caffeine caffeine) {
		CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
		caffeineCacheManager.setCaffeine(caffeine);
		return caffeineCacheManager;
	}

}
