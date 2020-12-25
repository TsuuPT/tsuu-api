package net.sandrohc.tsuu.api.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import net.sandrohc.tsuu.api.model.Media;
import net.sandrohc.tsuu.api.resolvers.types.ImageType;
import net.sandrohc.tsuu.api.resolvers.types.MediaTitleType;

@Component
@RequiredArgsConstructor
public class MediaResolver implements GraphQLResolver<Media> {

	public MediaTitleType title(Media media) {
		String _default = getDefaultTitle(media);
		return new MediaTitleType(_default, media.getTitlePortuguese(), media.getTitleEnglish(), media.getTitleRomanji(), media.getTitleOriginal());
	}

	public ImageType cover(Media media) {
		return new ImageType(media.getCoverSmall(), media.getCoverMedium(), media.getCoverLarge());
	}

	private String getDefaultTitle(Media media) {
		// TODO: find place where we can transform the original values to trim()
		if (media.getTitlePortuguese() != null && !media.getTitlePortuguese().isEmpty()) {
			return media.getTitlePortuguese();
		} else if (media.getTitleEnglish() != null && !media.getTitleEnglish().isEmpty()) {
			return media.getTitleEnglish();
		} else if (media.getTitleRomanji() != null && !media.getTitleRomanji().isEmpty()) {
			return media.getTitleRomanji();
		} else if (media.getTitleOriginal() != null && !media.getTitleOriginal().isEmpty()) {
			return media.getTitleOriginal();
		} else {
			return "";
		}
	}

	public String description(Media media, boolean asHTML) {
		return asHTML ? media.getDescriptionHTML() : media.getDescription();
	}

}