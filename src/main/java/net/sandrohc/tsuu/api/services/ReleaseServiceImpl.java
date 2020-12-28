package net.sandrohc.tsuu.api.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.flogger.Flogger;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import net.sandrohc.tsuu.api.model.Release;
import net.sandrohc.tsuu.api.repositories.ReleaseDao;

@Service
@Flogger
@RequiredArgsConstructor
public class ReleaseServiceImpl implements ReleaseService {

	private final ReleaseDao releaseDao;


	@Override
	public Flux<Release> getAllByFansubId(ObjectId fansubId) {
		log.atInfo().log("Loading all releases for fansub %s", fansubId);
		return releaseDao.findAllByFansubId(fansubId);
	}

	@Override
	public Mono<Release> getById(ObjectId id) {
		log.atInfo().log("Loading release %d", id);
		return releaseDao.findById(id);
	}

	@Override
	public Mono<Release> save(Release release) {
		log.atInfo().log("Creating release: %s", release);
		return releaseDao.save(release);
	}

	@Override
	public Flux<Release> saveAll(Iterable<Release> releases) {
		return releaseDao.saveAll(releases);
	}
}
