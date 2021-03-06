package net.sandrohc.tsuu.api.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.flogger.Flogger;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import net.sandrohc.tsuu.api.model.Media;
import net.sandrohc.tsuu.api.repositories.MediaDao;

@Service
@Flogger
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

	private final MediaDao mediaDao;


	@Override
	public Flux<Media> getAll() {
		log.atInfo().log("Loading all media");
		return mediaDao.findAll();
	}

	@Override
	public Mono<Media> getById(ObjectId id) {
		log.atInfo().log("Loading media %s", id);
		return mediaDao.findById(id);
	}

	@Override
	public Mono<Media> getBySlug(String slug) {
		log.atInfo().log("Loading media '%s'", slug);
		return mediaDao.findBySlug(slug);
	}

	@Override
	public Mono<Media> save(Media media) {
		log.atInfo().log("Creating media: %s", media);
		return mediaDao.save(media);
	}
}
