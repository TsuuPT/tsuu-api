package net.sandrohc.tsuu.api.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.flogger.Flogger;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import net.sandrohc.tsuu.api.model.Fansub;
import net.sandrohc.tsuu.api.model.FansubLink;
import net.sandrohc.tsuu.api.model.FansubMember;
import net.sandrohc.tsuu.api.repositories.FansubDao;
import net.sandrohc.tsuu.api.repositories.FansubLinkDao;
import net.sandrohc.tsuu.api.repositories.FansubMemberDao;

@Service
@Flogger
@RequiredArgsConstructor
public class FansubServiceImpl implements FansubService {

	private final FansubDao fansubDao;
	private final FansubLinkDao fansubLinkDao;
	private final FansubMemberDao fansubMemberDao;


	@Override
	public Flux<Fansub> getAll() {
		log.atInfo().log("Loading all fansubs");
		return fansubDao.findAll();
	}

	@Override
	public Mono<Fansub> getById(Long id) {
		log.atInfo().log("Loading fansub %d", id);
		return fansubDao.findById(id);
	}

	@Override
	public Mono<Fansub> getBySlug(String slug) {
		log.atInfo().log("Loading fansub '%s'", slug);
		return fansubDao.findBySlug(slug);
	}

	@Override
	public Flux<FansubLink> getLinks(Long id) {
		return fansubLinkDao.findByFansubId(id);
	}

	@Override
	public Flux<FansubMember> getMembers(Long id) {
		return fansubMemberDao.findByFansubId(id);
	}

	@Override
	public Mono<Fansub> save(Fansub fansub) {
		log.atInfo().log("Creating fansub: %s", fansub);
		return fansubDao.save(fansub);
	}
}
