package net.sandrohc.tsuu.api.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import net.sandrohc.tsuu.api.model.Fansub;
import net.sandrohc.tsuu.api.model.Media;

@Repository
public interface MediaDao extends ReactiveCrudRepository<Media, Long> {

}
