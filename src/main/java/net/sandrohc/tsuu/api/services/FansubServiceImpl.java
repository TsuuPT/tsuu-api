package net.sandrohc.tsuu.api.services;

import java.time.OffsetDateTime;

import lombok.RequiredArgsConstructor;
import lombok.extern.flogger.Flogger;
import org.bson.types.ObjectId;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import net.sandrohc.tsuu.api.model.Fansub;
import net.sandrohc.tsuu.api.model.FansubRevision;
import net.sandrohc.tsuu.api.model.dto.FansubDTO;
import net.sandrohc.tsuu.api.model.dto.FansubRevisionDTO;
import net.sandrohc.tsuu.api.model.enums.RevisionStatus;
import net.sandrohc.tsuu.api.model.mapper.FansubMapper;
import net.sandrohc.tsuu.api.model.mapper.FansubRevisionMapper;
import net.sandrohc.tsuu.api.repositories.FansubDao;
import net.sandrohc.tsuu.api.repositories.FansubRevisionDao;

@Service
@Flogger
@RequiredArgsConstructor
public class FansubServiceImpl implements FansubService {

	private final FansubDao fansubDao;
	private final FansubRevisionDao fansubRevisionDao;

	private final FansubMapper fansubMapper;
	private final FansubRevisionMapper fansubRevisionMapper;


	@Override
	public Flux<Fansub> getAll() {
		log.atInfo().log("Loading all fansubs");
		return fansubDao.findAll();
	}

	@Override
	public Mono<Fansub> getById(String id) {
		log.atInfo().log("Loading fansub %s", id);
		return fansubDao.findById(new ObjectId(id));
	}

	@Cacheable(cacheNames = "fansubs") // TODO: double-check if the cache works, and create a system to evict the cache if data has changed
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

	@Override
	public Flux<FansubRevisionDTO> getRevisions(String fansubId, boolean onlyPending, boolean onlyMine) {
		// TODO: filter by the logged in user
		Flux<FansubRevision> flux = onlyPending
				? fansubRevisionDao.findAllByFansubIdAndStatus(new ObjectId(fansubId), RevisionStatus.PENDING)
				: fansubRevisionDao.findAllByFansubId(new ObjectId(fansubId));


		return flux
				.map(fansubRevisionMapper::toDTO);
	}

	@Override
	public Mono<FansubRevisionDTO> saveRevision(String revisionId, FansubDTO fansubDto) {
		// TODO: send email

		if (revisionId == null) {
			FansubRevision revision = new FansubRevision();
			revision.setFansub(fansubMapper.fromDTO(fansubDto));
			revision.setStatus(RevisionStatus.PENDING);
			revision.setCreatedBy(-1); // TODO: add user ID
			revision.setCreatedAt(OffsetDateTime.now());
			return fansubRevisionDao.save(revision).map(fansubRevisionMapper::toDTO);
		} else {
			return fansubRevisionDao.findById(new ObjectId(revisionId))
					.doOnNext(r -> {
						if (r.getStatus() != RevisionStatus.PENDING) {
							throw new IllegalStateException("cannot update closed revisions");
						}
					})
					.doOnNext(r -> {
						r.setFansub(fansubMapper.fromDTO(fansubDto));
						r.setCreatedBy(-1); // TODO: add user ID
						r.setCreatedAt(OffsetDateTime.now());
					})
					.flatMap(fansubRevisionDao::save)
					.map(fansubRevisionMapper::toDTO);
		}
	}

	@Override
	public Mono<Void> approveRevision(String revisionId) {
		// TODO: send email
		return fansubRevisionDao.findById(new ObjectId(revisionId))
				.doOnNext(r -> {
					if (r.getStatus() != RevisionStatus.PENDING) {
						throw new IllegalStateException("cannot update closed revisions");
					}
				})
				.doOnNext(r -> {
					r.setReviewedBy(-1); // TODO: add user ID
					r.setReviewedAt(OffsetDateTime.now());
					r.setStatus(RevisionStatus.APPROVED);
				})
				.flatMap(fansubRevisionDao::save)
				.flatMap(r -> fansubDao.findById(r.getFansub().getId())
						.doOnNext(f -> {
							// TODO: merge revision with original
							f.setName(r.getFansub().getName());
						})
						.flatMap(fansubDao::save)
				)
				.then();
	}

	@Override
	public Mono<Void> rejectRevision(String revisionId, String reason) {
		// TODO: send email
		return fansubRevisionDao.findById(new ObjectId(revisionId))
				.doOnNext(r -> {
					if (r.getStatus() != RevisionStatus.PENDING) {
						throw new IllegalStateException("cannot update closed revisions");
					}
				})
				.doOnNext(r -> {
					r.setReviewedBy(-1); // TODO: add user ID
					r.setReviewedAt(OffsetDateTime.now());
					r.setStatus(RevisionStatus.REJECTED);
					r.setComment(reason);
				})
				.flatMap(fansubRevisionDao::save)
				.then();
	}

}
