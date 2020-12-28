package net.sandrohc.tsuu.api.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import net.sandrohc.tsuu.api.model.enums.MediaType;

@Document
@CompoundIndex(def = "{ 'slug': 0, 'type': 0 }", unique = true)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Media {

	@Id
	@ToString.Include
	@EqualsAndHashCode.Include
	private ObjectId id;

	@ToString.Include
	@EqualsAndHashCode.Include
	private String slug;

	@Indexed
	private MediaType type;

	private String titlePortuguese;
	private String titleEnglish;
	private String titleRomanji;
	private String titleOriginal;

	private String description;

	private String coverSmall;
	private String coverMedium;
	private String coverLarge;

	// TODO: external links (MAL, AniDB, etc.)

}
