package net.sandrohc.tsuu.api.config;

import java.time.OffsetDateTime;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import net.sandrohc.tsuu.api.model.*;
import net.sandrohc.tsuu.api.model.enums.LinkType;
import net.sandrohc.tsuu.api.model.enums.MediaType;
import net.sandrohc.tsuu.api.repositories.FansubDao;
import net.sandrohc.tsuu.api.repositories.MediaDao;
import net.sandrohc.tsuu.api.repositories.ReleaseDao;
import net.sandrohc.tsuu.api.services.FansubService;
import net.sandrohc.tsuu.api.services.MediaService;
import net.sandrohc.tsuu.api.services.ReleaseService;

@Profile("dev")
@Configuration
@RequiredArgsConstructor
public class MongoLoaderConfig {

	private final FansubDao fansubDao;
	private final MediaDao mediaDao;
	private final ReleaseDao releaseDao;
	private final FansubService fansubService;
	private final MediaService mediaService;
	private final ReleaseService releaseService;

	@PostConstruct
	public void loadData() {
		fansubDao.count()
				.filter(count -> count == 0)
				.subscribe(ignored -> loadFansubs(fansubService));

		mediaDao.count()
				.filter(count -> count == 0)
				.subscribe(ignored -> loadMedia(mediaService));

		releaseDao.count()
				.filter(count -> count == 0)
				.subscribe(ignored -> loadReleases(releaseService, fansubService, mediaService));
	}

	private void loadFansubs(FansubService fansubService) {
		Fansub f1 = Fansub.builder()
				.slug("fate4anime") // TODO: use interceptors for this
				.name("Fate4Anime")
				.description("<img src=\"https://fate4anime.com/wp-content/uploads/2015/11/Fate4Anime-Logo.png\" /><p>A Fate4Anime é uma Fansub Portuguesa, cujo objetivo principal é lançar anime com a melhor qualidade possível. Somos um grupo motivado e trabalhador e tentamos dar sempre o nosso melhor em cada projeto.</p>")
				.links(Arrays.asList(
						FansubLink.builder().link("https://fate4anime.com").type(LinkType.WEBSITE).build(),
						FansubLink.builder().link("https://discord.com/invite/v54872Jc").type(LinkType.DISCORD).build()
				))
				.members(Arrays.asList(
						FansubMember.builder().name("Hitman").role("Fundador").contact("Hitman#8124").contactType(LinkType.DISCORD).build(),
						FansubMember.builder().name("JoosJoestar").role("Tradutor").build()
				))
				.createdBy(-1)
				.createdAt(OffsetDateTime.now()) // TODO: use interceptors for this
				.deleted(false)
				.build();

		fansubService.save(f1).subscribe();
	}

	private void loadMedia(MediaService mediaService) {
		Media m1 = Media.builder()
				.slug("sword-art-online") // TODO: use interceptors for this
				.type(MediaType.ANIME)
				.titlePortuguese("Sword Art Online")
				.titleEnglish("Sword Art Online")
				.titleRomanji("Sword Art Online")
				.titleOriginal("ソードアート・オンライン")
				.description("Escape was impossible until it was cleared; a game over would mean an actual <strong>death</strong>.")
				.coverSmall("https://s4.anilist.co/file/anilistcdn/media/manga/cover/small/nx51479-ufMREP6e1UkT.jpg")
				.coverMedium("https://s4.anilist.co/file/anilistcdn/media/manga/cover/medium/nx51479-ufMREP6e1UkT.jpg")
				.coverLarge("https://s4.anilist.co/file/anilistcdn/media/manga/cover/large/nx51479-ufMREP6e1UkT.jpg")
				.build();

		mediaService.save(m1).subscribe();
	}

	private void loadReleases(ReleaseService releaseService, FansubService fansubService, MediaService mediaService) {
		Fansub f1 = fansubService.getBySlug("fate4anime").block();
		Media m1 = mediaService.getBySlug("sword-art-online").block();
		assert f1 != null;
		assert m1 != null;

		Release r1 = Release.builder()
				.name("Ep. 1")
				.link("https://fate4anime.com/shingeki-no-bahamut-genesis-bluray-atualizacoes-de-projetos/")
				.fansubId(f1.getId())
				.mediaId(m1.getId())
				.timestamp(OffsetDateTime.now()) // TODO: use interceptors for this
				.build();

		Release r2 = Release.builder()
				.name("Ep. 2")
				.link("https://fate4anime.com/shingeki-no-bahamut-genesis-bluray-atualizacoes-de-projetos/")
				.fansubId(f1.getId())
				.mediaId(m1.getId())
				.timestamp(OffsetDateTime.now()) // TODO: use interceptors for this
				.build();

		releaseService.saveAll(Arrays.asList(r1, r2)).subscribe();
	}

}
