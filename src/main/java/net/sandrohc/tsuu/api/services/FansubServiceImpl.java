package net.sandrohc.tsuu.api.services;

import java.time.OffsetDateTime;

import lombok.RequiredArgsConstructor;
import lombok.extern.flogger.Flogger;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import net.sandrohc.tsuu.api.model.Fansub;
import net.sandrohc.tsuu.api.model.FansubRevision;
import net.sandrohc.tsuu.api.model.enums.RevisionStatus;
import net.sandrohc.tsuu.api.repositories.FansubDao;
import net.sandrohc.tsuu.api.repositories.FansubRevisionDao;

@Service
@Flogger
@RequiredArgsConstructor
public class FansubServiceImpl implements FansubService {

	private final FansubDao fansubDao;
	private final FansubRevisionDao fansubRevisionDao;


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

	@Override
	public Flux<FansubRevision> getRevisions(ObjectId fansubId, boolean onlyPending, boolean onlyMine) {
		// TODO: filter by the logged in user
		return onlyPending
			? fansubRevisionDao.findAllByFansubIdAndStatus(fansubId, RevisionStatus.PENDING)
			: fansubRevisionDao.findAllByFansubId(fansubId);
	}

	@Override
	public Mono<FansubRevision> saveRevision(ObjectId revisionId, Fansub fansub) {
		// TODO: send email

		if (revisionId == null) {
			FansubRevision revision = new FansubRevision();
			revision.setFansub(fansub);
			revision.setStatus(RevisionStatus.PENDING);
			revision.setCreatedBy(-1); // TODO: add user ID
			revision.setCreatedAt(OffsetDateTime.now());
			return fansubRevisionDao.save(revision);
		} else {
			return fansubRevisionDao.findById(revisionId)
					.doOnNext(r -> {
						if (r.getStatus() != RevisionStatus.PENDING) {
							throw new IllegalStateException("cannot update closed revisions");
						}
					})
					.doOnNext(r -> {
						r.setFansub(fansub);
						r.setCreatedBy(-1); // TODO: add user ID
						r.setCreatedAt(OffsetDateTime.now());
					})
					.flatMap(fansubRevisionDao::save);
		}
	}

	@Override
	public Mono<Void> approveRevision(ObjectId revisionId) {
		// TODO: send email
		return fansubRevisionDao.findById(revisionId)
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
	public Mono<Void> rejectRevision(ObjectId revisionId, String reason) {
		// TODO: send email
		return fansubRevisionDao.findById(revisionId)
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
