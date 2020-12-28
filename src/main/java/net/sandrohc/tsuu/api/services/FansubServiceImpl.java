package net.sandrohc.tsuu.api.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.flogger.Flogger;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import net.sandrohc.tsuu.api.model.Fansub;
import net.sandrohc.tsuu.api.repositories.FansubDao;

@Service
@Flogger
@RequiredArgsConstructor
public class FansubServiceImpl implements FansubService {

	private final FansubDao fansubDao;


	@Override
	public Flux<Fansub> getAll() {
		log.atInfo().log("Loading all fansubs");
		return fansubDao.findAll();
	}

	@Override
	public Mono<Fansub> getById(ObjectId id) {
		log.atInfo().log("Loading fansub %s", id);
		return fansubDao.findById(id);
	}

	@Override
	public Mono<Fansub> getBySlug(String slug) {
		log.atInfo().log("Loading fansub '%s'", slug);
		return fansubDao.findBySlug(slug);
	}

	@Override
	public Mono<Fansub> save(Fansub fansub) {
		log.atInfo().log("Creating fansub: %s", fansub);
		return fansubDao.save(fansub);
	}
}
